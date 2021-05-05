package com.wappiter.app.module.login.presenter

import com.wappiter.app.R
import com.wappiter.app.module.login.router.LoginRouter
import com.wappiter.app.presentation.base.BasePresenter
import com.wappiter.app.presentation.base.CustomViewInjector
import com.wappiter.app.presentation.base.invoker.InteractorExecution
import com.wappiter.app.presentation.base.invoker.InteractorInvoker
import com.wappiter.app.util.bus.OnInitSessionEvent
import com.wappiter.domain.base.interactor.baseerrors.ApiInteractorError
import com.wappiter.domain.base.interactor.baseerrors.BaseInteractorError
import com.wappiter.domain.base.interactor.baseerrors.InteractorError
import com.wappiter.domain.firebase.FirebaseAnalyticsDatasource
import com.wappiter.domain.user.UserSession
import com.wappiter.domain.user.login.interactor.LoginInteractor
import com.wappiter.domain.user.login.interactor.LoginInteractorValues
import com.wappiter.domain.user.loginwithidentity.interactor.LoginWithIdentityInteractor
import com.wappiter.domain.user.logout.interactor.LogoutInteractor
import com.wappiter.infrastructure.accesor.ResourcesAccessor
import com.wappiter.infrastructure.exceptions.ErrorCodes
import org.greenrobot.eventbus.EventBus

class LoginPresenterImp(interactorInvoker: InteractorInvoker,
                        val loginInteractor: LoginInteractor,
                        val loginWithIdentityInteractor: LoginWithIdentityInteractor, //TODO para cuando se implementen los logins sociales
                        val mRouter: LoginRouter,
                        firebaseAnalyticsDatasource: FirebaseAnalyticsDatasource,
                        customViewInjector: CustomViewInjector,
                        logoutInteractor: LogoutInteractor,
                        resourcesAccessor: ResourcesAccessor) :
        BasePresenter<LoginView>(interactorInvoker, firebaseAnalyticsDatasource, customViewInjector, logoutInteractor, mRouter, resourcesAccessor), LoginPresenter {

    override fun didClickLoginButton(email: String, password: String) {
        view?.showProgressDialog()
        loginInteractor.setInteractorValues(LoginInteractorValues(email, password))
        InteractorExecution(loginInteractor).result { result -> onLoginSuccess(result) }.error(BaseInteractorError::class.java) { this.onLoginFailure(it) }.execute(mInteractorInvoker)
    }

    override fun didClickRegisterButton() {
        mRouter.goToRegister()
    }

    override fun didClickResetPasswordButton() {
        mRouter.goToResetPassword()
    }

    override fun didClickToolbarBackButton() {
        mRouter.goToBack()
    }

    private fun onLoginSuccess(userSession: UserSession) {
        view?.hideProgressDialog()
        if (userSession.user.hasMissingData) {
            mRouter.goToCompleteUserData()
            mRouter.goToBack()
        } else {
            EventBus.getDefault().post(OnInitSessionEvent())
            mRouter.goToBack()
        }
    }

    private fun onLoginFailure(error: InteractorError) {
        view?.hideProgressDialog()
        if (error is ApiInteractorError) {
            val exceptionCode = error.apiError.code

            if (ErrorCodes.User.INVALID_EMAIL_OR_PASSWORD == exceptionCode) {
                view?.showError(mResourcesAccessor.getString(R.string.login_error_email_or_password_incorrect_title),
                        mResourcesAccessor.getString(R.string.login_error_email_or_password_incorrect_message))
                return
            } else if (ErrorCodes.User.EMAIL_NOT_VALIDATED == exceptionCode) {
                view?.showError(mResourcesAccessor.getString(R.string.login_error_email_not_validated))
                return
            }
        }
        handleError(error)
    }

//    override fun onLoginWithGoogleClick() {
//        view?.signInWithGoogle()
//    }
//
//    override fun onLoginWithFacebookClick() {
//        view?.signInWithFacebook()
//    }
//
//    override fun onLoginWithGoogleSuccess(firstName: String, lastName: String, email: String, photoUrl: String?, userId: String, idToken: String) {
//        loginWithIdentityInteractor.setInteractorValues(LoginWithIdentityInteractorValues(firstName, lastName, email, photoUrl, userId, idToken, AppConstants.GOOGLE_PROVIDER))
//        InteractorExecution(loginWithIdentityInteractor).result { result -> onLoginSuccess(result) }.error(BaseInteractorError::class.java) { this.onLoginFailure(it) }.execute(mInteractorInvoker)
//    }
//
//    override fun onLoginWithGoogleFailed(e: ApiException) {
//        view?.showError("Google sign in failed: " + e.localizedMessage)
//    }
//
//    override fun onLoginWithFacebookSuccess(name: String, email: String, photoUrl: String?, userId: String, idToken: String) {
//        loginWithIdentityInteractor.setInteractorValues(LoginWithIdentityInteractorValues(name, "", email, photoUrl, userId, idToken, AppConstants.FACEBOOK_PROVIDER))
//        InteractorExecution(loginWithIdentityInteractor).result { result -> onLoginSuccess(result) }.error(BaseInteractorError::class.java) { this.onLoginFailure(it) }.execute(mInteractorInvoker)
//    }
//
//    override fun onLoginWithFacebookFailed(e: FacebookException) {
//        view?.showError("Facebook sign in failed: " + e.localizedMessage)
//    }
//
//    override fun onLoginWithFacebookCredentialFailed(e: Exception?) {
//        view?.showError("Facebook sign in failed: " + e?.localizedMessage)
//    }
//
//    override fun onLoginWithFacebookCancel(message: String) {
//        //Nothing to do
//    }
}