package com.wappiter.data.firebase

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import com.google.firebase.analytics.FirebaseAnalytics
import com.wappiter.domain.firebase.FirebaseAnalyticsDatasource

/**
 * Created by porta on 30/10/2018.
 */
class FirebaseAnalyticsDatasourceImp : FirebaseAnalyticsDatasource {
    private var mFirebaseAnalytics: FirebaseAnalytics
    private var mActivity: Activity? = null

    constructor(context: Context?) {
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(context!!)
    }

    constructor(activity: Activity?) {
        mActivity = activity
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(activity!!)
    }

    override fun setCurrentScreen(screenName: String) {
        mFirebaseAnalytics.setCurrentScreen(mActivity!!, screenName, mActivity!!.javaClass.simpleName)
        setCustomScreenName(screenName)
    }

    private fun setCustomScreenName(screenName: String) {
        val params = Bundle()
        params.putString(Param.SCREEN_NAME, screenName)
        logEvent(Event.CUSTOM_SCREEN_VIEW, params)
    }

    private fun logEvent(event: String,
                         params: Bundle) {
        mFirebaseAnalytics.logEvent(event, params)
        log(event, params)
    }

    private fun log(event: String,
                    params: Bundle) {
        Log.i(TAG, "Sending event: " + event + printBundle(params))
    }

    private fun printBundle(bundle: Bundle): String {
        if (bundle.isEmpty) {
            return ""
        }
        val builder = StringBuilder()
        builder.append(" -> ")
        for (key in bundle.keySet()) {
            val value = bundle[key]
            builder.append("[")
            builder.append(key)
            builder.append("]=[")
            builder.append(value)
            builder.append("]")
        }
        return builder.toString()
    }

    private object Event {
        const val CUSTOM_SCREEN_VIEW = "custom_screen_view"
    }

    private object Param {
        const val SCREEN_NAME = "screen_name"
    }

    companion object {
        private val TAG = FirebaseAnalyticsDatasourceImp::class.java.simpleName
    }
}