package com.wappiter.app.module.main.fragments.myrestaurants.presenter

import com.wappiter.app.data.constants.environment.EnvironmentConstants
import com.wappiter.app.module.main.fragments.myrestaurants.router.MyRestaurantsFragmentRouter
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

class MyRestaurantsFragmentPresenterImp(interactorInvoker: InteractorInvoker,
                                        val mGetUserLoggedInteractor: GetUserLoggedInteractor,
                                        logoutInteractor: LogoutInteractor,
                                        resourcesAccessor: ResourcesAccessor,
                                        firebaseAnalyticsDatasource: FirebaseAnalyticsDatasource,
                                        val mRouter: MyRestaurantsFragmentRouter,
                                        customViewInjector: CustomViewInjector) :
        BasePresenter<MyRestaurantsFragmentView>(interactorInvoker, firebaseAnalyticsDatasource, customViewInjector, logoutInteractor, mRouter, resourcesAccessor), MyRestaurantFragmentPresenter {

    private var mIsLoggedUser = false
    private var mIsFirstTimeItsSelected = true

    override fun startFlow() {
        getUserLogged()
    }

    override fun didClickEnterOnWappiter() {
        mRouter.goToLogin(false)
    }

    override fun didClickEmptyListButton() {
        view?.selectScannerTab()
    }

    override fun didClickRestaurant(url: String) {
        val restaurantId = url.subSequence(EnvironmentConstants.RESTAURANTS_URL.length, url.length)
        mRouter.goToRestaurantDetail(restaurantId.toString())
    }

    override fun didOnSelectMyRestaurantTab() {
        if (mIsFirstTimeItsSelected && !mIsLoggedUser) {
            mIsFirstTimeItsSelected = false
            view?.showLoginInfoDialog()
        }
    }

    override fun didOnInitSession() {
        startFlow()
    }

    override fun didOnCloseSession() {
        startFlow()
    }

    override fun didOnAddFavouriteRestaurantEvent() {
        startFlow()
    }

    private fun getUserLogged() {
        InteractorExecution(mGetUserLoggedInteractor).result { this.onGetUserLoggedSuccess(it) }.error(BaseInteractorError::class.java) { this.onGetUserLoggedFailure(it) }.execute(mInteractorInvoker)
    }

    private fun onGetUserLoggedSuccess(userSession: UserSession) {
        mIsLoggedUser = true
        if (userSession.user.hasFavourites) {
            view?.hideEmptyListView()
            view?.showMyRestaurantsWebview()
            view?.setupMyRestaurantsWebview(EnvironmentConstants.FAVOURITES_RESTAURANTS_URL, userSession.apiKey)
        } else {
            view?.hideMyRestaurantsWebview()
            view?.showEmptyListView()
        }
    }

    private fun onGetUserLoggedFailure(error: InteractorError) {
        mIsLoggedUser = false
        view?.hideMyRestaurantsWebview()
        view?.showEmptyListView()
    }
}