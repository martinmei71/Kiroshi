package com.wappiter.app.module.myorders.presenter

import com.wappiter.app.data.constants.environment.EnvironmentConstants
import com.wappiter.app.module.myorders.router.MyOrdersRouter
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

class MyOrdersPresenterImp(interactorInvoker: InteractorInvoker,
                           val mGetUserLoggedInteractor: GetUserLoggedInteractor,
                           logoutInteractor: LogoutInteractor,
                           resourcesAccessor: ResourcesAccessor,
                           firebaseAnalyticsDatasource: FirebaseAnalyticsDatasource,
                           val mRouter: MyOrdersRouter,
                           customViewInjector: CustomViewInjector) :
        BasePresenter<MyOrdersView>(interactorInvoker, firebaseAnalyticsDatasource, customViewInjector, logoutInteractor, mRouter, resourcesAccessor), MyOrdersPresenter {

    override fun startFlow(restaurantId: String) {
        getUserLogged(restaurantId)
    }

    override fun didClickToolbarBackButton() {
        mRouter.goToBack()
    }

    override fun didClickCloseErrorMessage() {
        mRouter.goToBack()
    }

    override fun didLaunchSwipeRefresh() {
        view?.reloadWebview()
    }

    private fun getUserLogged(restaurantId: String) {
        InteractorExecution(mGetUserLoggedInteractor).result { this.onGetUserLoggedSuccess(it, restaurantId) }.error(BaseInteractorError::class.java) { this.onGetUserLoggedFailure(it) }.execute(mInteractorInvoker)
    }

    private fun onGetUserLoggedSuccess(userSession: UserSession, restaurantId: String) {
        view?.initWebview(getMyOrdersUrl(restaurantId), userSession.apiKey)
    }

    private fun onGetUserLoggedFailure(error: InteractorError) {
        view?.showErrorOnGetUser()
    }

    private fun getMyOrdersUrl(restaurantId: String): String {
        return EnvironmentConstants.RESTAURANTS_URL + restaurantId + EnvironmentConstants.MY_ORDERS_URL
    }
}