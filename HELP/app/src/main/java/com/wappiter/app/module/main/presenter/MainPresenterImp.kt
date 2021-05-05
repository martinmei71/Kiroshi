package com.wappiter.app.module.splash.presenter

import com.wappiter.app.module.main.presenter.MainPresenter
import com.wappiter.app.module.main.router.MainRouter
import com.wappiter.app.presentation.base.BasePresenter
import com.wappiter.app.presentation.base.CustomViewInjector
import com.wappiter.app.presentation.base.invoker.InteractorExecution
import com.wappiter.app.presentation.base.invoker.InteractorInvoker
import com.wappiter.app.util.bus.OnAddFavouriteRestaurantEvent
import com.wappiter.domain.base.interactor.baseerrors.BaseInteractorError
import com.wappiter.domain.base.interactor.baseerrors.InteractorError
import com.wappiter.domain.firebase.FirebaseAnalyticsDatasource
import com.wappiter.domain.restaurant.addfavourite.interactor.AddFavouriteInteractor
import com.wappiter.domain.restaurant.addfavourite.interactor.AddFavouritesInteractorValues
import com.wappiter.domain.user.UserSession
import com.wappiter.domain.user.logout.interactor.LogoutInteractor
import com.wappiter.domain.user.profile.interactor.GetUserLoggedInteractor
import com.wappiter.domain.user.profile.interactor.UserProfileInteractor
import com.wappiter.infrastructure.accesor.ResourcesAccessor
import org.greenrobot.eventbus.EventBus

class MainPresenterImp(interactorInvoker: InteractorInvoker,
                       val mGetUserLoggedInteractor: GetUserLoggedInteractor,
                       val mUserProfileInteractor: UserProfileInteractor,
                       val mAddFavouriteInteractor: AddFavouriteInteractor,
                       logoutInteractor: LogoutInteractor,
                       resourcesAccessor: ResourcesAccessor,
                       firebaseAnalyticsDatasource: FirebaseAnalyticsDatasource,
                       val mRouter: MainRouter,
                       customViewInjector: CustomViewInjector) :
        BasePresenter<MainView>(interactorInvoker, firebaseAnalyticsDatasource, customViewInjector, logoutInteractor, mRouter, resourcesAccessor), MainPresenter {

    override fun startFlow() {
        view?.configureViewPager()
        getUserLogged()
    }

    override fun startFlowWithRestaurantId(restaurantId: String) {
        view?.configureViewPager()
        getUserLogged(restaurantId)
    }

    private fun getUserLogged(restaurantId: String? = null) {
        view?.showProgressDialog()
        InteractorExecution(mGetUserLoggedInteractor).result { this.onGetUserLoggedSuccess(it, restaurantId) }.error(BaseInteractorError::class.java) { this.onGetUserLoggedFailure(it, restaurantId) }.execute(mInteractorInvoker)
    }

    private fun onGetUserLoggedSuccess(userSession: UserSession, restaurantId: String?) {
        view?.hideProgressDialog()
        if (userSession.user.hasMissingData) {
            mRouter.goToCompleteUserData()
        } else if (restaurantId != null) {
            addFavourite(restaurantId)
        }
    }

    private fun onGetUserLoggedFailure(error: InteractorError, restaurantId: String?) {
        view?.hideProgressDialog()
        if (restaurantId != null) {
            mRouter.goToRestaurantDetail(restaurantId)
        }
    }

    private fun addFavourite(restaurantId: String) {
        view?.showProgressDialog()
        mAddFavouriteInteractor.setInteractorValues(AddFavouritesInteractorValues(restaurantId))
        InteractorExecution(mAddFavouriteInteractor).result { onAddFavouriteSuccess(restaurantId) }.error(BaseInteractorError::class.java) { this.onAddFavouriteFailure(it, restaurantId) }.execute(mInteractorInvoker)
    }

    private fun onAddFavouriteSuccess(restaurantId: String) {
        getUpdatedUserProfile(restaurantId)
    }

    private fun onAddFavouriteFailure(error: InteractorError, restaurantId: String) {
        view?.hideProgressDialog()
        mRouter.goToRestaurantDetail(restaurantId)
    }

    private fun getUpdatedUserProfile(restaurantId: String) {
        InteractorExecution(mUserProfileInteractor).result { this.onGetUpdatedUserProfileSuccess(it, restaurantId) }.error(BaseInteractorError::class.java) { this.onGetUpdatedUserProfileFailure(it, restaurantId) }.execute(mInteractorInvoker)
    }

    private fun onGetUpdatedUserProfileSuccess(userSession: UserSession, restaurantId: String) {
        view?.hideProgressDialog()
        EventBus.getDefault().post(OnAddFavouriteRestaurantEvent())
        mRouter.goToRestaurantDetail(restaurantId)
    }

    private fun onGetUpdatedUserProfileFailure(error: InteractorError, restaurantId: String) {
        view?.hideProgressDialog()
        mRouter.goToRestaurantDetail(restaurantId)
    }
}