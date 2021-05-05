package com.wappiter.app.module.registration.presenter

import com.wappiter.app.data.constants.environment.EnvironmentConstants
import com.wappiter.app.module.registration.router.RegistrationRouter
import com.wappiter.app.presentation.base.BasePresenter
import com.wappiter.app.presentation.base.CustomViewInjector
import com.wappiter.app.presentation.base.invoker.InteractorInvoker
import com.wappiter.domain.firebase.FirebaseAnalyticsDatasource
import com.wappiter.domain.user.logout.interactor.LogoutInteractor
import com.wappiter.domain.user.registration.interactor.RegistrationInteractor
import com.wappiter.infrastructure.accesor.ResourcesAccessor

class RegistrationPresenterImp(interactorInvoker: InteractorInvoker,
                               val mRegistrationInteractor: RegistrationInteractor,
                               val mRouter: RegistrationRouter,
                               firebaseAnalyticsDatasource: FirebaseAnalyticsDatasource,
                               customViewInjector: CustomViewInjector,
                               logoutInteractor: LogoutInteractor,
                               resourcesAccessor: ResourcesAccessor) :
        BasePresenter<RegistrationView>(interactorInvoker, firebaseAnalyticsDatasource, customViewInjector, logoutInteractor, mRouter, resourcesAccessor), RegistrationPresenter {

    override fun startFlow() {
        view?.initWebview(EnvironmentConstants.REGISTER_URL)
    }

    override fun didClickToolbarBackButton() {
        mRouter.goToBack()
    }

    override fun goToUrl(url: String) {
        mRouter.goToGenericWebview(url)
    }
}