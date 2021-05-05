package com.wappiter.app.util

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.Resources
import android.net.ConnectivityManager
import android.net.Uri
import android.util.TypedValue
import android.view.inputmethod.InputMethodManager

object Utils {
    fun isEmpty(string: String?): Boolean {
        return string == null || "" == string
    }

    fun isNotEmpty(value: String?): Boolean {
        return !isEmpty(value)
    }

    fun launchShareFileIntent(context: Context?,
                              message: String?) {
        val sendIntent = Intent()
        sendIntent.action = Intent.ACTION_SEND
        sendIntent.putExtra(Intent.EXTRA_TEXT, message)
        sendIntent.type = "text/plain"
        context!!.startActivity(sendIntent)
    }

    fun launchURL(context: Context,
                  url: String?) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        context.startActivity(browserIntent)
    }

//    fun sendMail(activity: Activity,
//                 email: String,
//                 subject: String?,
//                 streamUri: Uri?) {
//        val i = Intent(Intent.ACTION_SEND)
//        i.type = "message/rfc822"
//        i.putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
//        i.putExtra(Intent.EXTRA_SUBJECT, subject)
//        if (streamUri != null) {
//            i.putExtra(Intent.EXTRA_STREAM, streamUri)
//        }
//        try {
//            activity.startActivity(Intent.createChooser(i, activity.getString(R.string.send_mail_action)))
//        } catch (ex: ActivityNotFoundException) {
//            Toast.makeText(activity, R.string.send_mail_error, Toast.LENGTH_SHORT).show()
//        }
//    }

    fun hideKeyboard(activity: Activity) {
        if (activity.currentFocus != null) {
            val inputMethodManager = activity
                    .getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(activity.currentFocus.windowToken, 0)
        }
    }

    fun launchMarket(context: Context) {
        val appName = context.packageName
        try {
            context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$appName")))
        } catch (anfe: ActivityNotFoundException) {
            context.startActivity(Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id=$appName")))
        }
    }

    fun dpToPx(r: Resources,
               dp: Int): Int {
        return java.lang.Float.valueOf(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), r.displayMetrics))
                .toInt()
    }

    fun getAppVersionName(context: Context): String {
        try {
            val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
            return packageInfo.versionName
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        return ""
    }

    fun getAppVersionCode(context: Context): Int {
        try {
            val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
            return packageInfo.versionCode
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        return -1
    }

    fun isInternetAvailable(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo
        return netInfo != null && netInfo.isConnectedOrConnecting
    }

    fun getNetworkType(context: Context): String {
        if (isConnectedToAWiFi(context)) {
            return "wifi"
        }
        return if (isConnectedToAData(context)) {
            "wwan"
        } else "unknown"
    }

    private fun isConnectedToAWiFi(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null && activeNetwork.type == ConnectivityManager.TYPE_WIFI
    }

    private fun isConnectedToAData(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null && activeNetwork.type == ConnectivityManager.TYPE_MOBILE
    }
}