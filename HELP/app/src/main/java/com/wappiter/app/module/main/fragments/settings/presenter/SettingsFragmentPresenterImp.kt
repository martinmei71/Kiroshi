package com.wappiter.app.module.main.fragments.settings.presenter

import com.wappiter.app.data.constants.environment.EnvironmentConstants
import com.wappiter.app.module.main.fragments.settings.router.SettingsFragmentRouter
import com.wappiter.app.presentation.base.BasePresenter
import com.wappiter.app.presentation.base.CustomViewInjector
import com.wappiter.app.presentation.base.invoker.InteractorExecution
import com.wappiter.app.presentation.base.invoker.InteractorInvoker
import com.wappiter.app.util.bus.OnCloseSessionEvent
import com.wappiter.domain.base.interactor.baseerrors.BaseInteractorError
import com.wappiter.domain.base.interactor.baseerrors.InteractorError
import com.wappiter.domain.firebase.FirebaseAnalyticsDatasource
import com.wappiter.domain.user.UserSession
import com.wappiter.domain.user.logout.interactor.LogoutInteractor
import com.wappiter.domain.user.profile.interactor.GetUserLoggedInteractor
import com.wappiter.infrastructure.accesor.ResourcesAccessor
import org.greenrobot.eventbus.EventBus

class SettingsFragmentPresenterImp(interactorInvoker: InteractorInvoker,
                                   val mGetUserLoggedInteractor: GetUserLoggedInteractor,
                                   val mLogoutInteractor: LogoutInteractor,
                                   resourcesAccessor: ResourcesAccessor,
                                   firebaseAnalyticsDatasource: FirebaseAnalyticsDatasource,
                                   val mRouter: SettingsFragmentRouter,
                                   customViewInjector: CustomViewInjector) :
        BasePresenter<SettingsFragmentView>(interactorInvoker, firebaseAnalyticsDatasource, customViewInjector, mLogoutInteractor, mRouter, resourcesAccessor), SettingsFragmentPresenter {

    override fun startFlow() {
        getUserLogged()
    }

    override fun didClickLoginButton() {
        mRouter.goToLogin(false)
    }

    override fun didClickMyProfileButton() {
        mRouter.goToGenericWebview(EnvironmentConstants.EDIT_PROFILE_URL)
    }

    override fun didClickTermsAndConditionsButton() {
        mRouter.goToGenericWebview(EnvironmentConstants.TERMS_AND_CONDITIONS_URL)
    }

    override fun didClickWebButton() {
        mRouter.goToExternalURL(EnvironmentConstants.WEB_URL)
    }

    override fun didClickLogoutButton() {
        this.logout()
    }

    override fun didOnInitSession() {
        startFlow()
    }

    override fun didClickRestaurantManagerButton() {
        mRouter.goToExternalURL(EnvironmentConstants.RESTAURANT_MANAGER_REGISTER_URL)
    }

    override fun didClickRateAppButton() {
        view?.goToStore()
    }

    override fun logout() {
        InteractorExecution(mLogoutInteractor).result { this.onCloseSessionSuccess() }.error(BaseInteractorError::class.java) { this.onCloseSessionFailure(it) }.execute(mInteractorInvoker)
    }

    private fun onCloseSessionSuccess() {
        startFlow()
        EventBus.getDefault().post(OnCloseSessionEvent())
    }

    private fun onCloseSessionFailure(error: InteractorError?) {
        startFlow()
        EventBus.getDefault().post(OnCloseSessionEvent())
    }

    private fun getUserLogged() {
        InteractorExecution(mGetUserLoggedInteractor).result { this.onGetUserLoggedSuccess(it) }.error(BaseInteractorError::class.java) { this.onGetUserLoggedFailure(it) }.execute(mInteractorInvoker)
    }

    private fun onGetUserLoggedSuccess(userSession: UserSession) {
        view?.hideLoginButton()
        view?.showMyProfileButton()
        view?.showLogoutButton()
    }

    private fun onGetUserLoggedFailure(error: InteractorError) {
        view?.hideMyProfileButton()
        view?.hideLogoutButton()
        view?.showLoginButton()
    }
}