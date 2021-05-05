package com.wappiter.app.module.splash

import android.app.Activity
import com.wappiter.app.infrastucture.di.ActivityScope
import com.wappiter.app.module.splash.presenter.SplashPresenter
import com.wappiter.app.module.splash.presenter.SplashPresenterImp
import com.wappiter.app.module.splash.router.SplashRouter
import com.wappiter.app.module.splash.router.SplashRouterImp
import com.wappiter.app.presentation.base.CustomViewInjector
import com.wappiter.app.presentation.base.invoker.InteractorInvoker
import com.wappiter.data.ApiRetrofit
import com.wappiter.data.appsession.AppSessionDatasourceImp
import com.wappiter.data.appsession.api.result.AppSessionApiModel
import com.wappiter.data.appsession.api.result.AppSessionApiModelMapper
import com.wappiter.data.firebase.FirebaseAnalyticsDatasourceImp
import com.wappiter.data.user.UserApiModel
import com.wappiter.data.user.UserApiModelSessionMapper
import com.wappiter.data.user.loginwithapikey.LoginWithApiKeyDatasourceImp
import com.wappiter.domain.appsession.AppSession
import com.wappiter.domain.appsession.data.AppSessionDatasource
import com.wappiter.domain.appsession.interactor.AppSessionInteractor
import com.wappiter.domain.appsession.service.AppSessionService
import com.wappiter.domain.appsession.service.AppSessionServiceImp
import com.wappiter.domain.base.Mapper
import com.wappiter.domain.firebase.FirebaseAnalyticsDatasource
import com.wappiter.domain.user.UserSession
import com.wappiter.domain.user.loginwithapikey.data.LoginWithApiKeyDatasource
import com.wappiter.domain.user.loginwithapikey.interactor.LoginWithApiKeyInteractor
import com.wappiter.domain.user.loginwithapikey.service.LoginWithApiKeyService
import com.wappiter.domain.user.loginwithapikey.service.LoginWithApiKeyServiceImp
import com.wappiter.domain.user.logout.interactor.LogoutInteractor
import com.wappiter.domain.user.profile.interactor.GetUserLoggedInteractor
import com.wappiter.domain.user.session.UserSessionLocalDatasource
import com.wappiter.domain.user.session.service.UserSessionService
import com.wappiter.infrastructure.accesor.ResourcesAccessor
import com.wappiter.infrastructure.datasource.AppPreferencesDatasource
import com.wappiter.infrastructure.interactor.InteractorErrorHandler
import dagger.Module
import dagger.Provides

@Module
open class SplashModule(activity: Activity) {

    val mActivity = activity

    @ActivityScope
    @Provides
    open fun providePresenter(interactorInvoker: InteractorInvoker, getUserLoggedInteractor: GetUserLoggedInteractor, loginWithApiKeyInteractor: LoginWithApiKeyInteractor, appSessionInteractor: AppSessionInteractor, logoutInteractor: LogoutInteractor, resourcesAccessor: ResourcesAccessor, firebaseAnalyticsDatasource: FirebaseAnalyticsDatasource, router: SplashRouter, customViewInjector: CustomViewInjector): SplashPresenter {

        return SplashPresenterImp(interactorInvoker, getUserLoggedInteractor, loginWithApiKeyInteractor, appSessionInteractor, logoutInteractor, resourcesAccessor, firebaseAnalyticsDatasource, router, customViewInjector)
    }

    @ActivityScope
    @Provides
    fun provideGetUserLoggedInteractor(userSessionService: UserSessionService, interactorErrorHandler: InteractorErrorHandler): GetUserLoggedInteractor {
        return GetUserLoggedInteractor(userSessionService, interactorErrorHandler)
    }

    @ActivityScope
    @Provides
    fun provideGetAppSessionInteractor(appSessionService: AppSessionService, interactorErrorHandler: InteractorErrorHandler): AppSessionInteractor {
        return AppSessionInteractor(appSessionService, interactorErrorHandler)
    }

    @ActivityScope
    @Provides
    fun provideAppSessionService(appSessionDatasource: AppSessionDatasource, appPreferencesDatasource: AppPreferencesDatasource): AppSessionService {

        return AppSessionServiceImp(appSessionDatasource, appPreferencesDatasource)
    }

    @ActivityScope
    @Provides
    fun provideAppSessionDatasource(apiRetrofit: ApiRetrofit, mapper: Mapper<AppSessionApiModel, AppSession>): AppSessionDatasource {
        return AppSessionDatasourceImp(apiRetrofit, mapper)
    }

    @ActivityScope
    @Provides
    fun provideLoginWithApiKeyInteractor(loginWithApiKeyService: LoginWithApiKeyService, interactorErrorHandler: InteractorErrorHandler): LoginWithApiKeyInteractor {
        return LoginWithApiKeyInteractor(loginWithApiKeyService, interactorErrorHandler)
    }

    @ActivityScope
    @Provides
    fun provideWithApiKeyLoginService(loginWithApiKeyDatasource: LoginWithApiKeyDatasource, userSessionLocalDatasource: UserSessionLocalDatasource): LoginWithApiKeyService {
        return LoginWithApiKeyServiceImp(loginWithApiKeyDatasource, userSessionLocalDatasource)
    }

    @ActivityScope
    @Provides
    fun provideLoginWithApiKeyDatasource(retrofit: ApiRetrofit, mapper: Mapper<UserApiModel, UserSession>): LoginWithApiKeyDatasource {
        return LoginWithApiKeyDatasourceImp(retrofit, mapper)
    }

    @ActivityScope
    @Provides
    fun provideAppSessionMapper(): Mapper<AppSessionApiModel, AppSession> {
        return AppSessionApiModelMapper()
    }

    @ActivityScope
    @Provides
    fun provideUserApiModelMapper(): Mapper<UserApiModel, UserSession> {
        return UserApiModelSessionMapper()
    }

    @ActivityScope
    @Provides
    fun provideSplashRouter(): SplashRouter {
        return SplashRouterImp(mActivity)
    }

    @ActivityScope
    @Provides
    fun provideFirebaseAnalyticsDatasource(): FirebaseAnalyticsDatasource {
        return FirebaseAnalyticsDatasourceImp(mActivity)
    }
}