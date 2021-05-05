package com.wappiter.app.module.restaurantdetail

import android.app.Activity
import com.wappiter.app.infrastucture.di.ActivityScope
import com.wappiter.app.module.restaurantdetail.presenter.RestaurantDetailPresenter
import com.wappiter.app.module.restaurantdetail.presenter.RestaurantDetailPresenterImp
import com.wappiter.app.module.restaurantdetail.router.RestaurantDetailRouter
import com.wappiter.app.module.restaurantdetail.router.RestaurantDetailRouterImp
import com.wappiter.app.presentation.base.CustomViewInjector
import com.wappiter.app.presentation.base.invoker.InteractorInvoker
import com.wappiter.data.firebase.FirebaseAnalyticsDatasourceImp
import com.wappiter.domain.firebase.FirebaseAnalyticsDatasource
import com.wappiter.domain.user.logout.interactor.LogoutInteractor
import com.wappiter.domain.user.profile.interactor.GetUserLoggedInteractor
import com.wappiter.domain.user.session.service.UserSessionService
import com.wappiter.infrastructure.accesor.ResourcesAccessor
import com.wappiter.infrastructure.interactor.InteractorErrorHandler
import dagger.Module
import dagger.Provides

@Module
open class RestaurantDetailModule(activity: Activity) {

    val mActivity = activity

    @ActivityScope
    @Provides
    open fun providePresenter(interactorInvoker: InteractorInvoker, getUserLoggedInteractor: GetUserLoggedInteractor, logoutInteractor: LogoutInteractor, resourcesAccessor: ResourcesAccessor, firebaseAnalyticsDatasource: FirebaseAnalyticsDatasource, router: RestaurantDetailRouter, customViewInjector: CustomViewInjector): RestaurantDetailPresenter {
        return RestaurantDetailPresenterImp(interactorInvoker, getUserLoggedInteractor, logoutInteractor, resourcesAccessor, firebaseAnalyticsDatasource, router, customViewInjector)
    }

    @ActivityScope
    @Provides
    fun provideGetUserLoggedInteractor(userSessionService: UserSessionService, interactorErrorHandler: InteractorErrorHandler): GetUserLoggedInteractor {
        return GetUserLoggedInteractor(userSessionService, interactorErrorHandler)
    }

    @ActivityScope
    @Provides
    fun provideRestaurantDetailRouter(): RestaurantDetailRouter {
        return RestaurantDetailRouterImp(mActivity)
    }

    @ActivityScope
    @Provides
    fun provideFirebaseAnalyticsDatasource(): FirebaseAnalyticsDatasource {
        return FirebaseAnalyticsDatasourceImp(mActivity)
    }
}