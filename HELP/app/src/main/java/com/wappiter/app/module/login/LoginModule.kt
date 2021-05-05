package com.wappiter.app.module.login

import android.app.Activity
import com.wappiter.app.infrastucture.di.ActivityScope
import com.wappiter.app.module.login.presenter.LoginPresenter
import com.wappiter.app.module.login.presenter.LoginPresenterImp
import com.wappiter.app.module.login.router.LoginRouter
import com.wappiter.app.module.login.router.LoginRouterImp
import com.wappiter.app.presentation.base.CustomViewInjector
import com.wappiter.app.presentation.base.invoker.InteractorInvoker
import com.wappiter.data.ApiRetrofit
import com.wappiter.data.firebase.FirebaseAnalyticsDatasourceImp
import com.wappiter.data.user.UserApiModel
import com.wappiter.data.user.UserApiModelSessionMapper
import com.wappiter.data.user.login.LoginDatasourceImp
import com.wappiter.data.user.loginwithidentity.LoginWithIdentityDatasourceImp
import com.wappiter.data.user.registration.RegistrationDatasourceImp
import com.wappiter.domain.base.Mapper
import com.wappiter.domain.firebase.FirebaseAnalyticsDatasource
import com.wappiter.domain.user.UserSession
import com.wappiter.domain.user.login.data.LoginDatasource
import com.wappiter.domain.user.login.interactor.LoginInteractor
import com.wappiter.domain.user.login.service.LoginService
import com.wappiter.domain.user.login.service.LoginServiceImp
import com.wappiter.domain.user.loginwithidentity.data.LoginWithIdentityDatasource
import com.wappiter.domain.user.loginwithidentity.interactor.LoginWithIdentityInteractor
import com.wappiter.domain.user.loginwithidentity.service.LoginWithIdentityService
import com.wappiter.domain.user.loginwithidentity.service.LoginWithIdentityServiceImp
import com.wappiter.domain.user.logout.interactor.LogoutInteractor
import com.wappiter.domain.user.registration.data.RegistrationDatasource
import com.wappiter.domain.user.registration.service.RegistrationService
import com.wappiter.domain.user.registration.service.RegistrationServiceImp
import com.wappiter.domain.user.session.UserSessionLocalDatasource
import com.wappiter.infrastructure.accesor.ResourcesAccessor
import com.wappiter.infrastructure.interactor.InteractorErrorHandler
import dagger.Module
import dagger.Provides

@Module
class LoginModule(val mActivity: Activity) {

    @ActivityScope
    @Provides
    fun providePresenter(interactorInvoker: InteractorInvoker, loginInteractor: LoginInteractor, loginWithIdentityInteractor: LoginWithIdentityInteractor, loginRouter: LoginRouter, firebaseAnalyticsDatasource: FirebaseAnalyticsDatasource,
                         customViewInjector: CustomViewInjector, logoutInteractor: LogoutInteractor, resourcesAccessor: ResourcesAccessor): LoginPresenter {
        return LoginPresenterImp(interactorInvoker, loginInteractor, loginWithIdentityInteractor, loginRouter, firebaseAnalyticsDatasource, customViewInjector, logoutInteractor, resourcesAccessor)
    }

    @ActivityScope
    @Provides
    fun provideLoginInteractor(loginService: LoginService, interactorErrorHandler: InteractorErrorHandler): LoginInteractor {
        return LoginInteractor(loginService, interactorErrorHandler)
    }

    @ActivityScope
    @Provides
    fun provideLoginService(loginDatasource: LoginDatasource, userSessionLocalDatasource: UserSessionLocalDatasource): LoginService {
        return LoginServiceImp(loginDatasource, userSessionLocalDatasource)
    }

    @ActivityScope
    @Provides
    fun provideLoginDatasource(retrofit: ApiRetrofit, mapper: Mapper<UserApiModel, UserSession>): LoginDatasource {
        return LoginDatasourceImp(retrofit, mapper)
    }

    @ActivityScope
    @Provides
    fun provideLoginWithIdentityInteractor(loginWithIdentityService: LoginWithIdentityService, registrationService: RegistrationService, interactorErrorHandler: InteractorErrorHandler): LoginWithIdentityInteractor {
        return LoginWithIdentityInteractor(loginWithIdentityService, registrationService, interactorErrorHandler)
    }

    @ActivityScope
    @Provides
    fun provideLoginWithIdentityService(loginWithIdentityDatasource: LoginWithIdentityDatasource, userSessionLocalDatasource: UserSessionLocalDatasource): LoginWithIdentityService {
        return LoginWithIdentityServiceImp(loginWithIdentityDatasource, userSessionLocalDatasource)
    }

    @ActivityScope
    @Provides
    fun provideLoginWithIdentityDatasource(retrofit: ApiRetrofit, mapper: Mapper<UserApiModel, UserSession>): LoginWithIdentityDatasource {
        return LoginWithIdentityDatasourceImp(retrofit, mapper)
    }

    @ActivityScope
    @Provides
    fun provideRegistrationService(registrationDatasource: RegistrationDatasource, userSessionLocalDatasource: UserSessionLocalDatasource): RegistrationService {
        return RegistrationServiceImp(registrationDatasource, userSessionLocalDatasource)
    }

    @ActivityScope
    @Provides
    fun provideRegistrationDatasource(retrofit: ApiRetrofit, mapper: Mapper<UserApiModel, UserSession>): RegistrationDatasource {
        return RegistrationDatasourceImp(retrofit, mapper)
    }

    @ActivityScope
    @Provides
    fun provideUserApiModelMapper(): Mapper<UserApiModel, UserSession> {
        return UserApiModelSessionMapper()
    }

    @ActivityScope
    @Provides
    fun provideFirebaseAnalyticsDatasource(): FirebaseAnalyticsDatasource {
        return FirebaseAnalyticsDatasourceImp(mActivity)
    }

    @ActivityScope
    @Provides
    fun provideLoginRouter(): LoginRouter {
        return LoginRouterImp(mActivity)
    }
}