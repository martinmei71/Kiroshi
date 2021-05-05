package com.wappiter.app.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import com.google.firebase.iid.FirebaseInstanceId
import com.wappiter.app.BuildConfig
import com.wappiter.app.module.splash.SplashActivity
import okhttp3.OkHttpClient
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

/**
 * Created by porta on 18/05/17.
 */
object SystemUtils {
    val appVersionAndBuildType: String
        get() = BuildConfig.VERSION_NAME + " (" + BuildConfig.BUILD_TYPE + ")"

    val appVersion: String
        get() = BuildConfig.VERSION_NAME

    val deviceManufacturer: String
        get() = if (Build.MANUFACTURER == null) "" else Build.MANUFACTURER

    val deviceModel: String
        get() = if (Build.MODEL == null) "" else Build.MODEL

    val androidReleaseNumber: String
        get() = if (Build.VERSION.RELEASE == null) "" else Build.VERSION.RELEASE

    val appCode: String
        get() = "AndroidApp"

    val operatingSystem: String
        get() = "Android"

    val osVersion: String
        get() = if (Build.VERSION.RELEASE == null) "" else Build.VERSION.RELEASE

    val uniqueIdentifier: String
        get() = FirebaseInstanceId.getInstance().id

    fun packageName(context: Context?): String {
        return if (context != null) {
            context.packageName
        } else ""
    }

    fun restartApp(activity: Activity) {
        val intent = Intent(activity, SplashActivity::class.java)
        activity.startActivity(intent)
        activity.finish()
        Runtime.getRuntime().exit(0) // Kill kill kill!
    }

    // Create a trust manager that does not validate certificate chains
    val unsafeOkHttpClient:

    // Install the all-trusting trust manager

    // Create an ssl socket factory with our all-trusting manager
            OkHttpClient.Builder
        get() = try {

            // Create a trust manager that does not validate certificate chains
            val trustAllCerts = arrayOf<TrustManager>(
                    object : X509TrustManager {
                        @Throws(CertificateException::class)
                        override fun checkClientTrusted(chain: Array<X509Certificate>,
                                                        authType: String) {
                        }

                        @Throws(CertificateException::class)
                        override fun checkServerTrusted(chain: Array<X509Certificate>,
                                                        authType: String) {
                        }

                        override fun getAcceptedIssuers(): Array<X509Certificate> {
                            return arrayOf()
                        }
                    })

            // Install the all-trusting trust manager
            val sslContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustAllCerts, SecureRandom())

            // Create an ssl socket factory with our all-trusting manager
            val sslSocketFactory = sslContext.socketFactory
            val builder = OkHttpClient.Builder()
            builder.sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
            builder.hostnameVerifier(HostnameVerifier { p0, p1 -> true })
            builder
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
}