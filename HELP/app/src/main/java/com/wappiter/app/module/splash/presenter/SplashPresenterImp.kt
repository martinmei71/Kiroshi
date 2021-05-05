package com.wappiter.app.module.splash.presenter

import com.wappiter.app.module.splash.router.SplashRouter
import com.wappiter.app.presentation.base.BasePresenter
import com.wappiter.app.presentation.base.CustomViewInjector
import com.wappiter.app.presentation.base.invoker.InteractorExecution
import com.wappiter.app.presentation.base.invoker.InteractorInvoker
import com.wappiter.domain.appsession.interactor.AppSessionInteractor
import com.wappiter.domain.appsession.interactor.AppSessionInteractorValues
import com.wappiter.domain.base.interactor.baseerrors.BaseInteractorError
import com.wappiter.domain.base.interactor.baseerrors.InteractorError
import com.wappiter.domain.base.interactorerror.ExpiredAppVersionInteractorError
import com.wappiter.domain.firebase.FirebaseAnalyticsDatasource
import com.wappiter.domain.user.UserSession
import com.wappiter.domain.user.loginwithapikey.interactor.LoginWithApiKeyInteractor
import com.wappiter.domain.user.logout.interactor.LogoutInteractor
import com.wappiter.domain.user.profile.interactor.GetUserLoggedInteractor
import com.wappiter.infrastructure.accesor.ResourcesAccessor

class SplashPresenterImp(interactorInvoker: InteractorInvoker,
                         val mGetUserLoggedInteractor: GetUserLoggedInteractor,
                         val mLoginWithApiKeyInteractor: LoginWithApiKeyInteractor,
                         val mAppSessionInteractor: AppSessionInteractor,
                         logoutInteractor: LogoutInteractor,
                         resourcesAccessor: ResourcesAccessor,
                         firebaseAnalyticsDatasource: FirebaseAnalyticsDatasource,
                         val mRouter: SplashRouter,
                         customViewInjector: CustomViewInjector) :
        BasePresenter<SplashView>(interactorInvoker, firebaseAnalyticsDatasource, customViewInjector, logoutInteractor, mRouter, resourcesAccessor), SplashPresenter {

    private var restaurantId = ""

    override fun startFlow() {
        view?.onActionsDone()
    }

    override fun initApp(appSessionInput: AppSessionInput, restaurantId: String) {
        this.restaurantId = restaurantId
        createAppSession(appSessionInput)
    }

    override fun didClickGoToStore() {
        view?.goToStore()
    }

    private fun createAppSession(appSessionInput: AppSessionInput) {
        mAppSessionInteractor.setInteractorValues(AppSessionInteractorValues(appSessionInput.appVersionCode, appSessionInput.appVersionName, appSessionInput.networkType, appSessionInput.deviceUniqueIdentifier, appSessionInput.mobileOperatingSystemName, appSessionInput.mobileOperatingSystemVersionName, appSessionInput.deviceManufacturerName, appSessionInput.deviceModelName))
        InteractorExecution(mAppSessionInteractor).result { onCreateAppSessionSuccess() }.error(BaseInteractorError::class.java) { this.onCreateAppSessionFailure(it) }.execute(mInteractorInvoker)
    }

    private fun onCreateAppSessionSuccess() {
        getUserLogged()
    }

    private fun onCreateAppSessionFailure(error: InteractorError) {
        when (error) {
            is ExpiredAppVersionInteractorError -> {
                view?.showDialogExpireVersion()
            }

            else -> {
                getUserLogged()
            }
        }
    }

    private fun getUserLogged() {
        InteractorExecution(mGetUserLoggedInteractor).result { this.onGetUserLoggedSuccess(it) }.error(BaseInteractorError::class.java) { this.onGetUserLoggedFailure(it) }.execute(mInteractorInvoker)
    }

    private fun onGetUserLoggedSuccess(userSession: UserSession?) {
        loginWithApiKey()
    }

    private fun onGetUserLoggedFailure(error: InteractorError) {
        goToNextActivity()
    }

    private fun loginWithApiKey() {
        InteractorExecution(mLoginWithApiKeyInteractor).result { this.onLoginWithApiKeySuccess(it) }.error(BaseInteractorError::class.java) { this.onLoginWithApiKeyFailure(it) }.execute(mInteractorInvoker)
    }

    private fun onLoginWithApiKeySuccess(userSession: UserSession) {
        goToNextActivity()
    }

    private fun onLoginWithApiKeyFailure(error: InteractorError) {
        goToNextActivity()
    }

    private fun goToNextActivity() {
        if (restaurantId.isEmpty()) {
            mRouter.goToTermsAndConditons()
        } else {
            mRouter.goToTermsAndConditonsFromDeeplink(restaurantId)
        }
    }
}