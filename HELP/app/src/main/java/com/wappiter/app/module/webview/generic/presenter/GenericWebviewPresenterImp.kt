package com.wappiter.app.module.webview.generic.presenter

import com.wappiter.app.module.webview.generic.router.GenericWebviewRouter
import com.wappiter.app.presentation.base.BasePresenter
import com.wappiter.app.presentation.base.CustomViewInjector
import com.wappiter.app.presentation.base.invoker.InteractorExecution
import com.wappiter.app.presentation.base.invoker.InteractorInvoker
import com.wappiter.domain.base.interactor.baseerrors.BaseInteractorError
import com.wappiter.domain.base.interactor.baseerrors.InteractorError
import com.wappiter.domain.firebase.FirebaseAnalyticsDatasource
import com.wappiter.domain.user.UserSession
import com.wappiter.domain.user.logout.interactor.LogoutInteractor
import com.wappiter.domain.user.profile.interactor.GetUserLoggedInteractor
import com.wappiter.infrastructure.accesor.ResourcesAccessor

class GenericWebviewPresenterImp(interactorInvoker: InteractorInvoker,
                                 val mGetUserLoggedInteractor: GetUserLoggedInteractor,
                                 logoutInteractor: LogoutInteractor,
                                 resourcesAccessor: ResourcesAccessor,
                                 firebaseAnalyticsDatasource: FirebaseAnalyticsDatasource,
                                 val mRouter: GenericWebviewRouter,
                                 customViewInjector: CustomViewInjector) :
        BasePresenter<GenericWebviewView>(interactorInvoker, firebaseAnalyticsDatasource, customViewInjector, logoutInteractor, mRouter, resourcesAccessor), GenericWebviewPresenter {

    override fun startFlow(url: String) {
        getUserLogged(url)
    }

    override fun didClickToolbarBackButton() {
        mRouter.goToBack()
    }

    private fun getUserLogged(url: String) {
        InteractorExecution(mGetUserLoggedInteractor).result { this.onGetUserLoggedSuccess(it, url) }.error(BaseInteractorError::class.java) { this.onGetUserLoggedFailure(it, url) }.execute(mInteractorInvoker)
    }

    private fun onGetUserLoggedSuccess(userSession: UserSession, url: String) {
        view?.initWebview(url, userSession.apiKey)
    }

    private fun onGetUserLoggedFailure(error: InteractorError, url: String) {
        view?.initWebview(url)
    }
}