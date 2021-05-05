package com.wappiter.app.util.webview

import android.app.Activity
import com.wappiter.app.constants.AppConstants
import java.util.*
import kotlin.collections.HashMap

class WebviewUtils {

    fun getWebviewHeaders(userApiKey: String?): HashMap<String, String> {
        val headerMap: HashMap<String, String> = HashMap()
        headerMap[AppConstants.USER_AGENT_HEADER_WEBVIEW] = AppConstants.USER_AGENT_VALUE_WEBVIEW
        headerMap[AppConstants.LOCALE_HEADER_WEBVIEW] = Locale.getDefault().toString()
        if (userApiKey != null) {
            headerMap[AppConstants.API_KEY_HEADER_WEBVIEW] = userApiKey
        }
        return headerMap
    }
}