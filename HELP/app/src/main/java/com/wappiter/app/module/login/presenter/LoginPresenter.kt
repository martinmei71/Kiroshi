package com.wappiter.app.module.login.presenter

import com.wappiter.app.presentation.base.BaseVP

interface LoginPresenter : BaseVP.Presenter {

    fun didClickLoginButton(email: String, password: String)

    fun didClickRegisterButton()

    fun didClickResetPasswordButton()

    fun didClickToolbarBackButton()

//    fun onLoginWithGoogleClick()
//
//    fun onLoginWithFacebookClick()
//
//    fun onLoginWithGoogleSuccess(firstName: String, lastName: String, email: String, photoUrl: String?, userId: String, idToken: String)
//
//    fun onLoginWithGoogleFailed(e: ApiException)
//
//    fun onLoginWithFacebookSuccess(name: String, email: String, photoUrl: String?, userId: String, idToken: String)
//
//    fun onLoginWithFacebookFailed(e: FacebookException)
//
//    fun onLoginWithFacebookCredentialFailed(e: Exception?)
//
//    fun onLoginWithFacebookCancel(message: String)
}