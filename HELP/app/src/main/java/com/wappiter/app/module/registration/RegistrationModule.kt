package com.wappiter.app.module.registration

import android.app.Activity
import com.wappiter.app.infrastucture.di.ActivityScope
import com.wappiter.app.module.registration.presenter.RegistrationPresenter
import com.wappiter.app.module.registration.presenter.RegistrationPresenterImp
import com.wappiter.app.module.registration.router.RegistrationRouter
import com.wappiter.app.module.registration.router.RegistrationRouterImp
import com.wappiter.app.presentation.base.CustomViewInjector
import com.wappiter.app.presentation.base.invoker.InteractorInvoker
import com.wappiter.data.ApiRetrofit
import com.wappiter.data.firebase.FirebaseAnalyticsDatasourceImp
import com.wappiter.data.user.UserApiModel
import com.wappiter.data.user.UserApiModelSessionMapper
import com.wappiter.data.user.registration.RegistrationDatasourceImp
import com.wappiter.domain.base.Mapper
import com.wappiter.domain.firebase.FirebaseAnalyticsDatasource
import com.wappiter.domain.user.UserSession
import com.wappiter.domain.user.logout.interactor.LogoutInteractor
import com.wappiter.domain.user.registration.data.RegistrationDatasource
import com.wappiter.domain.user.registration.interactor.RegistrationInteractor
import com.wappiter.domain.user.registration.service.RegistrationService
import com.wappiter.domain.user.registration.service.RegistrationServiceImp
import com.wappiter.domain.user.session.UserSessionLocalDatasource
import com.wappiter.infrastructure.accesor.ResourcesAccessor
import com.wappiter.infrastructure.interactor.InteractorErrorHandler
import dagger.Module
import dagger.Provides

@Module
class RegistrationModule(val mActivity: Activity) {

    @ActivityScope
    @Provides
    fun providePresenter(interactorInvoker: InteractorInvoker, registrationInteractor: RegistrationInteractor, registrationRouter: RegistrationRouter, firebaseAnalyticsDatasource: FirebaseAnalyticsDatasource, customViewInjector: CustomViewInjector, logoutInteractor: LogoutInteractor, resourcesAccessor: ResourcesAccessor): RegistrationPresenter {
        return RegistrationPresenterImp(interactorInvoker, registrationInteractor, registrationRouter, firebaseAnalyticsDatasource, customViewInjector, logoutInteractor, resourcesAccessor)
    }

    @ActivityScope
    @Provides
    fun provideRegistrationInteractor(registrationService: RegistrationService, interactorErrorHandler: InteractorErrorHandler): RegistrationInteractor {
        return RegistrationInteractor(registrationService, interactorErrorHandler)
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
    fun provideRegistrationRouter(): RegistrationRouter {
        return RegistrationRouterImp(mActivity)
    }
}