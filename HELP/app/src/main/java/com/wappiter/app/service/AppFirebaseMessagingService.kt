package com.wappiter.app.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.util.Log
import android.util.Log.d
import android.util.Log.e
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.TaskStackBuilder
import androidx.core.content.ContextCompat
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.wappiter.app.BuildConfig
import com.wappiter.app.R
import com.wappiter.app.infrastucture.RetrofitServiceGenerator
import com.wappiter.app.util.Utils
import com.wappiter.data.ApiRetrofit
import com.wappiter.data.user.device.api.DeviceApiModel
import com.wappiter.data.user.device.api.DeviceRequestParams
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

class AppFirebaseMessagingService : FirebaseMessagingService() {

    companion object {

        const val OS_ANDROID_VALUE = "android"

        private const val CHANNEL_ID = "channel_id"
        private val TAG = AppFirebaseMessagingService::class.toString()
    }

    override fun onMessageReceived(message: RemoteMessage) {

        val from = message.from
        val data = message.data

        if (message.data.isNotEmpty()) {

            d(TAG, "Message data payload: " + message.data)
            handleNotification(from, data)

        } else {

            d(TAG, "Not message data payload")
        }
    }

    override fun onNewToken(token: String) {

        super.onNewToken(token)
        sendRegistrationToServer(applicationContext)
    }

    private fun sendRegistrationToServer(context: Context) {

        try {

            FirebaseInstanceId.getInstance().instanceId
                    .addOnSuccessListener { instanceIdResult ->

                        val deviceUniqueId = instanceIdResult.id
                        val deviceToken = instanceIdResult.token

                        Log.i(TAG, "GCM Registration deviceUniqueId: $deviceUniqueId")
                        Log.i(TAG, "GCM Registration Token: $deviceToken")

                        registerDevice(context, deviceUniqueId, deviceToken)
                    }

        } catch (e: Exception) {

            d(TAG, "Failed to complete token refresh", e)
        }

    }

    private fun registerDevice(context: Context,
                               deviceUniqueId: String,
                               deviceToken: String) {

        if (BuildConfig.DEBUG) {

            d(TAG, "sendRegistrationToServer with deviceUniqueId: $deviceUniqueId")
            d(TAG, "sendRegistrationToServer with deviceToken: $deviceToken")
        }

        val apiRetrofit = RetrofitServiceGenerator.createService(ApiRetrofit::class.java)

        val version = Utils.getAppVersionCode(this)

        val deviceRequestParams = DeviceRequestParams(deviceToken, OS_ANDROID_VALUE, version.toString(), deviceUniqueId)

        val callRegisterDevice = apiRetrofit.updateDevice(deviceRequestParams)

        callRegisterDevice.enqueue(object : Callback<DeviceApiModel> {

            override fun onResponse(call: Call<DeviceApiModel>, response: Response<DeviceApiModel>) {

                if (response.isSuccessful) {

                    val deviceResponse = response.body()
                    onTokenUpload(deviceResponse!!.success, deviceResponse.errors)

                } else {

                    try {

                        onTokenUploadError(response.errorBody()!!.string())

                    } catch (e: IOException) {
                        e.printStackTrace()
                    }

                }
            }

            override fun onFailure(call: Call<DeviceApiModel>, t: Throwable) {

                onTokenUploadError(t.message)
            }
        })
    }


    private fun onTokenUpload(success: Boolean,
                              errors: String) {

        d(TAG, "onTokenUpload: $success (errors: $errors")
    }

    private fun onTokenUploadError(
            string: String?) {

        e(TAG, "onTokenUploadError: $string")
    }

    private fun handleNotification(from: String?, data: Map<*, *>) {

        val jsonObject = JSONObject(data)
        val message = JSONObject(jsonObject.getString("message"))
        val type = message.optString("type")

        if (type.equals("DataNotification", ignoreCase = true)) {
            sendPublicationNotification(message.optString("title"), message.optString("image"))
        }
    }

    private fun sendPublicationNotification(title: String, imageUrl: String) {

        val notificationIntent = Intent() // MainActivity.newIntentForNotification(application) TODO: descomentar esto cuando se requiera

        val contentIntent = TaskStackBuilder.create(this)
                .addNextIntentWithParentStack(notificationIntent)
                .getPendingIntent(requestId(), 0)

        val bigTextStyle = NotificationCompat.BigTextStyle()
        bigTextStyle.bigText(title)

        val builder = NotificationCompat.Builder(application.applicationContext)
        builder.setDefaults(android.app.Notification.DEFAULT_SOUND or android.app.Notification.DEFAULT_LIGHTS or android.app.Notification.DEFAULT_VIBRATE)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder//.setSmallIcon(R.drawable.wappiter_logo_splash)
                    .setColor(ContextCompat.getColor(application, R.color.primary_color))
                    .setContentTitle(resources.getString(R.string.app_name))
                    .setContentText(title)
                    .setStyle(NotificationCompat.BigTextStyle().bigText(title))
                    .setContentIntent(contentIntent)
                    .setAutoCancel(true).priority = NotificationCompat.PRIORITY_DEFAULT
        } else {
            builder.setContentTitle(resources.getString(R.string.app_name))
                    .setContentText(title)
                    .setStyle(NotificationCompat.BigTextStyle().bigText(title))
                    //.setSmallIcon(R.drawable.wappiter_logo_splash)
                    .setContentIntent(contentIntent)
                    .setAutoCancel(true)
        }

        builder.setLargeIcon(getBitmapFromURL(imageUrl))

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(CHANNEL_ID, resources.getString(R.string.app_name), NotificationManager.IMPORTANCE_HIGH)
            val notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
            builder.setChannelId(channel.id)
        }

        with(NotificationManagerCompat.from(this)) {
            // notificationId is a unique int for each notification that you must define
            notify(notificationId(), builder.build())
        }
    }

    private fun notificationId(): Int {
        return System.currentTimeMillis().toInt()
    }

    private fun requestId(): Int {
        return System.currentTimeMillis().toInt()
    }

    private fun getBitmapFromURL(imageUrl: String?): Bitmap? {
        if (imageUrl == null || imageUrl.equals("null", ignoreCase = true) || imageUrl.isEmpty()) {
            return null
        }
        try {
            var url: URL? = null
            try {
                if (BuildConfig.DEBUG) {
                    e(TAG, "imageUrl: $imageUrl")
                }
                url = URL(imageUrl)
            } catch (e: MalformedURLException) {
                e.printStackTrace()
                return null
            }

            val connection = url.openConnection() as HttpURLConnection
            connection.doInput = true
            connection.connect()
            val input = connection.inputStream
            return BitmapFactory.decodeStream(input)
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }

    }


}