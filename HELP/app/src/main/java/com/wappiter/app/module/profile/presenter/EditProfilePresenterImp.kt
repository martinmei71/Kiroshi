package com.wappiter.app.module.profile.presenter

import com.wappiter.app.data.constants.environment.EnvironmentConstants
import com.wappiter.app.module.profile.router.EditProfileRouter
import com.wappiter.app.presentation.base.BasePresenter
import com.wappiter.app.presentation.base.CustomViewInjector
import com.wappiter.app.presentation.base.invoker.InteractorExecution
import com.wappiter.app.presentation.base.invoker.InteractorInvoker
import com.wappiter.app.util.bus.OnInitSessionEvent
import com.wappiter.domain.base.interactor.baseerrors.BaseInteractorError
import com.wappiter.domain.base.interactor.baseerrors.InteractorError
import com.wappiter.domain.firebase.FirebaseAnalyticsDatasource
import com.wappiter.domain.user.UserSession
import com.wappiter.domain.user.logout.interactor.LogoutInteractor
import com.wappiter.domain.user.profile.interactor.GetUserLoggedInteractor
import com.wappiter.domain.user.profile.interactor.UserProfileInteractor
import com.wappiter.infrastructure.accesor.ResourcesAccessor
import org.greenrobot.eventbus.EventBus

class EditProfilePresenterImp(interactorInvoker: InteractorInvoker,
                              val mGetUserLoggedInteractor: GetUserLoggedInteractor,
                              val mUserProfileInteractor: UserProfileInteractor,
                              logoutInteractor: LogoutInteractor,
                              resourcesAccessor: ResourcesAccessor,
                              firebaseAnalyticsDatasource: FirebaseAnalyticsDatasource,
                              val mRouter: EditProfileRouter,
                              customViewInjector: CustomViewInjector) :
        BasePresenter<EditProfileView>(interactorInvoker, firebaseAnalyticsDatasource, customViewInjector, logoutInteractor, mRouter, resourcesAccessor), EditProfilePresenter {

    override fun startFlow() {
        getUserLogged()
    }

    override fun didClickToolbarBackButton() {
        checkIfUserCompletedTheData()
    }

    override fun checkIfUserCompletedTheData() {
        view?.showProgressDialog()
        getUpdatedUserProfile()
    }

    private fun getUserLogged() {
        InteractorExecution(mGetUserLoggedInteractor).result { this.onGetUserLoggedSuccess(it) }.error(BaseInteractorError::class.java) { this.onGetUserLoggedFailure(it) }.execute(mInteractorInvoker)
    }

    private fun onGetUserLoggedSuccess(userSession: UserSession) {
        view?.initWebview(EnvironmentConstants.EDIT_PROFILE_URL, userSession.apiKey)
    }

    private fun onGetUserLoggedFailure(error: InteractorError) {
        view?.initWebview(EnvironmentConstants.EDIT_PROFILE_URL)
    }

    private fun getUpdatedUserProfile() {
        InteractorExecution(mUserProfileInteractor).result { this.onGetUpdatedUserProfileSuccess(it) }.error(BaseInteractorError::class.java) { this.onGetUpdatedUserProfileFailure(it) }.execute(mInteractorInvoker)
    }

    private fun onGetUpdatedUserProfileSuccess(userSession: UserSession) {
        view?.hideProgressDialog()
        if (!userSession.user.hasMissingData) {
            EventBus.getDefault().post(OnInitSessionEvent())
            mRouter.goToBack()
        }
    }

    private fun onGetUpdatedUserProfileFailure(error: InteractorError) {
        view?.hideProgressDialog()
        checkIfUserCompletedTheData()
    }
}