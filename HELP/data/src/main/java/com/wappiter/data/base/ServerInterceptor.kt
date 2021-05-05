package com.wappiter.data.base

import android.content.Context
import com.google.gson.Gson
import com.wappiter.data.apppreferences.PreferencesHelper
import com.wappiter.data.apppreferences.PreferencesNames
import com.wappiter.domain.user.UserSession
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import java.util.*

class ServerInterceptor(context: Context?) : Interceptor {
    private val mPreferencesHelper: PreferencesHelper

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalHttpUrl = original.url
        val builder = originalHttpUrl.newBuilder()
        val url = builder.build()
        val requestBuilder = original.newBuilder().url(url)
        requestBuilder.addHeader(AUTHORIZATION_HEADER, authorizationHeaderValue)
        requestBuilder.addHeader(LOCALE_HEADER, Locale.getDefault().toString())
        val userSession = userSession
        if (userSession != null && userSession.apiKey.isNotEmpty()) {
            requestBuilder.addHeader(API_KEY_HEADER, userSession.apiKey)
        }
        val appSessionCode = appSessionCode
        if (appSessionCode != null && appSessionCode.isNotEmpty()) {
            requestBuilder.addHeader(APP_SESSION_CODE_HEADER, appSessionCode)
        }
        val request = requestBuilder.build()
        return chain.proceed(request)
    }

    private val userSession: UserSession?
        get() {
            val jsonUser = mPreferencesHelper.getPref(USER_SESSION_KEY, "")
            return if (jsonUser.isNotEmpty()) {
                Gson().fromJson(jsonUser, UserSession::class.java)
            } else null
        }

    private val appSessionCode: String?
        get() = mPreferencesHelper.getPref(PreferencesNames.APP_SESSION_CODE_KEY, "")

    // + Base64.encodeToString(BuildConfig.SERVER_CREDENTIALS.getBytes(), Base64.NO_WRAP);
    private val authorizationHeaderValue: String
        get() = "Basic " // + Base64.encodeToString(BuildConfig.SERVER_CREDENTIALS.getBytes(), Base64.NO_WRAP);

    companion object {
        private const val AUTHORIZATION_HEADER = "Authorization"
        private const val API_KEY_HEADER = "Api-Key"
        private const val LOCALE_HEADER = "Accept-Language"
        private const val APP_SESSION_CODE_HEADER = "Session-Code"
        private const val USER_SESSION_KEY = "user_session"
    }

    init {
        mPreferencesHelper = PreferencesHelper(context!!)
    }
}