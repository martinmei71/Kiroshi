package com.wappiter.app.presentation.base

/**
 * Created by porta on 18/5/17.
 */
interface BaseVP {
    interface View {
        fun setUpView()
        fun logInfo(message: String)
        fun logDebug(message: String)
        fun logError(error: String,
                     message: String)

        fun showError()
        fun showError(message: String)
        fun showError(title: String, message: String)
        fun showToastMessage(message: Int)

        fun showProgressDialog()
        fun hideProgressDialog()
    }

    interface Presenter {
        fun attachView(view: Any)
        fun onStart(screenName: String)
        fun detachView()
        fun logout()
    }
}