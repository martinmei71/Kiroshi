package com.wappiter.app.module.resetpassword.presenter

import com.wappiter.app.data.constants.environment.EnvironmentConstants
import com.wappiter.app.module.resetpassword.router.ResetPasswordRouter
import com.wappiter.app.presentation.base.BasePresenter
import com.wappiter.app.presentation.base.CustomViewInjector
import com.wappiter.app.presentation.base.invoker.InteractorInvoker
import com.wappiter.domain.firebase.FirebaseAnalyticsDatasource
import com.wappiter.domain.user.logout.interactor.LogoutInteractor
import com.wappiter.domain.user.requestresetpassword.interactor.RequestResetPasswordInteractor
import com.wappiter.infrastructure.accesor.ResourcesAccessor

class ResetPasswordPresenterImp(interactorInvoker: InteractorInvoker,
                                val mRequestResetPasswordInteractor: RequestResetPasswordInteractor,
                                val mRouter: ResetPasswordRouter,
                                logoutInteractor: LogoutInteractor,
                                firebaseAnalyticsDatasource: FirebaseAnalyticsDatasource,
                                resourcesAccessor: ResourcesAccessor,
                                customViewInjector: CustomViewInjector) :
        BasePresenter<ResetPasswordView>(interactorInvoker, firebaseAnalyticsDatasource, customViewInjector, logoutInteractor, mRouter, resourcesAccessor), ResetPasswordPresenter {


    override fun startFlow() {
        view?.initWebview(EnvironmentConstants.RESET_PASSWORD_URL)
    }

    override fun didClickToolbarBackButton() {
        mRouter.goToBack()
    }
}