package com.wappiter.app.module.profile

import android.app.Activity
import com.wappiter.app.infrastucture.di.ActivityScope
import com.wappiter.app.module.profile.presenter.EditProfilePresenter
import com.wappiter.app.module.profile.presenter.EditProfilePresenterImp
import com.wappiter.app.module.profile.router.EditProfileRouter
import com.wappiter.app.module.profile.router.EditProfileRouterImp
import com.wappiter.app.presentation.base.CustomViewInjector
import com.wappiter.app.presentation.base.invoker.InteractorInvoker
import com.wappiter.data.ApiRetrofit
import com.wappiter.data.firebase.FirebaseAnalyticsDatasourceImp
import com.wappiter.data.user.UserApiModel
import com.wappiter.data.user.UserApiModelSessionMapper
import com.wappiter.data.user.profile.UserProfileDatasourceImp
import com.wappiter.domain.base.Mapper
import com.wappiter.domain.firebase.FirebaseAnalyticsDatasource
import com.wappiter.domain.user.UserSession
import com.wappiter.domain.user.logout.interactor.LogoutInteractor
import com.wappiter.domain.user.profile.data.UserProfileDatasource
import com.wappiter.domain.user.profile.interactor.GetUserLoggedInteractor
import com.wappiter.domain.user.profile.interactor.UserProfileInteractor
import com.wappiter.domain.user.profile.service.UserProfileService
import com.wappiter.domain.user.profile.service.UserProfileServiceImp
import com.wappiter.domain.user.session.UserSessionLocalDatasource
import com.wappiter.domain.user.session.service.UserSessionService
import com.wappiter.infrastructure.accesor.ResourcesAccessor
import com.wappiter.infrastructure.interactor.InteractorErrorHandler
import dagger.Module
import dagger.Provides

@Module
open class EditProfileModule(activity: Activity) {

    val mActivity = activity

    @ActivityScope
    @Provides
    open fun providePresenter(interactorInvoker: InteractorInvoker, getUserLoggedInteractor: GetUserLoggedInteractor, userProfileInteractor: UserProfileInteractor, logoutInteractor: LogoutInteractor, resourcesAccessor: ResourcesAccessor, firebaseAnalyticsDatasource: FirebaseAnalyticsDatasource, router: EditProfileRouter, customViewInjector: CustomViewInjector): EditProfilePresenter {
        return EditProfilePresenterImp(interactorInvoker, getUserLoggedInteractor, userProfileInteractor, logoutInteractor, resourcesAccessor, firebaseAnalyticsDatasource, router, customViewInjector)
    }

    @ActivityScope
    @Provides
    fun provideGetUserLoggedInteractor(userSessionService: UserSessionService, interactorErrorHandler: InteractorErrorHandler): GetUserLoggedInteractor {
        return GetUserLoggedInteractor(userSessionService, interactorErrorHandler)
    }

    @ActivityScope
    @Provides
    fun provideUserProfileInteractor(userProfileService: UserProfileService, interactorErrorHandler: InteractorErrorHandler): UserProfileInteractor {
        return UserProfileInteractor(userProfileService, interactorErrorHandler)
    }

    @ActivityScope
    @Provides
    fun provideUserProfileService(userProfileDatasource: UserProfileDatasource, userSessionLocalDatasource: UserSessionLocalDatasource): UserProfileService {
        return UserProfileServiceImp(userProfileDatasource, userSessionLocalDatasource)
    }

    @ActivityScope
    @Provides
    fun provideUserProfileDatasource(retrofit: ApiRetrofit, mapper: Mapper<UserApiModel, UserSession>): UserProfileDatasource {
        return UserProfileDatasourceImp(retrofit, mapper)
    }

    @ActivityScope
    @Provides
    fun provideUserApiModelMapper(): Mapper<UserApiModel, UserSession> {
        return UserApiModelSessionMapper()
    }

    @ActivityScope
    @Provides
    fun provideEditProfileRouter(): EditProfileRouter {
        return EditProfileRouterImp(mActivity)
    }

    @ActivityScope
    @Provides
    fun provideFirebaseAnalyticsDatasource(): FirebaseAnalyticsDatasource {
        return FirebaseAnalyticsDatasourceImp(mActivity)
    }
}