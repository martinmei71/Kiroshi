package com.wappiter.app.base

/**
 * Created by porta on 18/5/17.
 */
interface BaseView {

    fun showGenericError()

    fun showConnectionError()

    fun logDebug(message: String?)

    fun logError(error: String?, message: String?)

    fun onUnauthorizedResponse()
}