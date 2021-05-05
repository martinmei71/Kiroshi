package com.wappiter.app.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.wifi.WifiManager

/**
 * Created by porta on 09/02/2018.
 */
object NetworkUtils {
    private const val NETWORK_TYPE_UNKNOWN = 0
    private const val NETWORK_TYPE_WIFI = 1
    private const val NETWORK_TYPE_DATA = 2
    private const val NETWORK_TYPE_UNKNOWN_VALUE = "unknown"
    private const val NETWORK_TYPE_WIFI_VALUE = "wifi"
    private const val NETWORK_TYPE_DATA_VALUE = "wwan"

    fun isInternetAvailable(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo
        return netInfo != null && netInfo.isConnectedOrConnecting
    }

    fun getCurrentWiFiSSID(context: Context): String? {
        if (isConnectedToAWiFi(context)) {
            val wifiManager = context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
            val wifiInfo = wifiManager.connectionInfo
            return wifiInfo.ssid
        }
        return null
    }

    fun getNetworkType(context: Context): Int {
        if (isConnectedToAWiFi(context)) {
            return NETWORK_TYPE_WIFI
        }
        return if (isConnectedToAData(context)) {
            NETWORK_TYPE_DATA
        } else NETWORK_TYPE_UNKNOWN
    }

    fun getNetworkTypeParam(context: Context): String {
        return when (getNetworkType(context)) {
            NETWORK_TYPE_WIFI -> NETWORK_TYPE_WIFI_VALUE
            NETWORK_TYPE_DATA -> NETWORK_TYPE_DATA_VALUE
            else -> NETWORK_TYPE_UNKNOWN_VALUE
        }
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