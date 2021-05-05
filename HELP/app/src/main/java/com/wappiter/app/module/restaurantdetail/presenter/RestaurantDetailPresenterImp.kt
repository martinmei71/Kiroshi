package com.wappiter.app.module.restaurantdetail.presenter

import com.wappiter.app.data.constants.environment.EnvironmentConstants
import com.wappiter.app.module.restaurantdetail.router.RestaurantDetailRouter
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

class RestaurantDetailPresenterImp(interactorInvoker: InteractorInvoker,
                                   val mGetUserLoggedInteractor: GetUserLoggedInteractor,
                                   logoutInteractor: LogoutInteractor,
                                   resourcesAccessor: ResourcesAccessor,
                                   firebaseAnalyticsDatasource: FirebaseAnalyticsDatasource,
                                   val mRouter: RestaurantDetailRouter,
                                   customViewInjector: CustomViewInjector) :
        BasePresenter<RestaurantDetailView>(interactorInvoker, firebaseAnalyticsDatasource, customViewInjector, logoutInteractor, mRouter, resourcesAccessor), RestaurantDetailPresenter {

    private var mRestaurantId: String = ""

    override fun startFlow(restaurantId: String) {
        mRestaurantId = restaurantId
        getUserLogged(restaurantId)
    }

    override fun didClickToolbarBackButton() {
        mRouter.goToBack()
    }

    override fun didClickMyOrdersButton() {
        getUserLoggedToGoMyOrders()
    }

    override fun interceptOpenLogin() {
        mRouter.goToLogin(false)
    }

    override fun didOnInitSession() {
        getUserLogged(mRestaurantId)
    }

    override fun didClickGoToLogin() {
        mRouter.goToLogin(false)
    }

    private fun getUserLogged(restaurantId: String) {
        InteractorExecution(mGetUserLoggedInteractor).result { this.onGetUserLoggedSuccess(it, restaurantId) }.error(BaseInteractorError::class.java) { this.onGetUserLoggedFailure(it, restaurantId) }.execute(mInteractorInvoker)
    }

    private fun onGetUserLoggedSuccess(userSession: UserSession, restaurantId: String) {
        view?.initWebview(getRestaurantUrl(restaurantId), userSession.apiKey)
    }

    private fun onGetUserLoggedFailure(error: InteractorError, restaurantId: String) {
        view?.initWebview(getRestaurantUrl(restaurantId))
    }

    private fun getRestaurantUrl(restaurantId: String): String {
        return EnvironmentConstants.RESTAURANTS_URL + restaurantId
    }

    private fun getUserLoggedToGoMyOrders() {
        InteractorExecution(mGetUserLoggedInteractor).result { this.onGetUserLoggedToGoMyOrdersSuccess(it) }.error(BaseInteractorError::class.java) { this.onGetUserLoggedToGoMyOrdersFailure(it) }.execute(mInteractorInvoker)
    }

    private fun onGetUserLoggedToGoMyOrdersSuccess(userSession: UserSession) {
        mRouter.goToMyOrders(mRestaurantId)
    }

    private fun onGetUserLoggedToGoMyOrdersFailure(error: InteractorError) {
        view?.showErrorNotUserLoggedToGoMyOrders()
    }

}