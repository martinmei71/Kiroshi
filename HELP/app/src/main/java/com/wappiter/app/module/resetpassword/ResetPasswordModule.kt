package com.wappiter.app.module.resetpassword

import android.app.Activity
import com.wappiter.app.infrastucture.di.ActivityScope
import com.wappiter.app.module.resetpassword.presenter.ResetPasswordPresenter
import com.wappiter.app.module.resetpassword.presenter.ResetPasswordPresenterImp
import com.wappiter.app.module.resetpassword.router.ResetPasswordRouter
import com.wappiter.app.module.resetpassword.router.ResetPasswordRouterImp
import com.wappiter.app.presentation.base.CustomViewInjector
import com.wappiter.app.presentation.base.invoker.InteractorInvoker
import com.wappiter.data.ApiRetrofit
import com.wappiter.data.firebase.FirebaseAnalyticsDatasourceImp
import com.wappiter.data.user.requestresetpassword.RequestResetPasswordDatasourceImp
import com.wappiter.domain.firebase.FirebaseAnalyticsDatasource
import com.wappiter.domain.user.logout.interactor.LogoutInteractor
import com.wappiter.domain.user.requestresetpassword.data.RequestResetPasswordDatasource
import com.wappiter.domain.user.requestresetpassword.interactor.RequestResetPasswordInteractor
import com.wappiter.domain.user.requestresetpassword.service.RequestResetPasswordService
import com.wappiter.domain.user.requestresetpassword.service.RequestResetPasswordServiceImp
import com.wappiter.infrastructure.accesor.ResourcesAccessor
import com.wappiter.infrastructure.interactor.InteractorErrorHandler
import dagger.Module
import dagger.Provides

@Module
class ResetPasswordModule(val mActivity: Activity) {

    @ActivityScope
    @Provides
    fun providePresenter(interactorInvoker: InteractorInvoker, requestResetPasswordInteractor: RequestResetPasswordInteractor, resetPasswordRouter: ResetPasswordRouter, logoutInteractor: LogoutInteractor, firebaseAnalyticsDatasource: FirebaseAnalyticsDatasource, resourcesAccessor: ResourcesAccessor, customViewInjector: CustomViewInjector): ResetPasswordPresenter {
        return ResetPasswordPresenterImp(interactorInvoker, requestResetPasswordInteractor, resetPasswordRouter, logoutInteractor, firebaseAnalyticsDatasource, resourcesAccessor, customViewInjector)
    }

    @ActivityScope
    @Provides
    fun provideRequestResetPasswordInteractor(requestResetPasswordService: RequestResetPasswordService, interactorErrorHandler: InteractorErrorHandler): RequestResetPasswordInteractor {
        return RequestResetPasswordInteractor(requestResetPasswordService, interactorErrorHandler)
    }

    @ActivityScope
    @Provides
    fun provideRequestResetPasswordService(requestResetPasswordDatasource: RequestResetPasswordDatasource): RequestResetPasswordService {
        return RequestResetPasswordServiceImp(requestResetPasswordDatasource)
    }

    @ActivityScope
    @Provides
    fun provideRequestResetPasswordDatasource(retrofit: ApiRetrofit): RequestResetPasswordDatasource {
        return RequestResetPasswordDatasourceImp(retrofit)
    }

    @ActivityScope
    @Provides
    fun provideFirebaseAnalyticsDatasource(): FirebaseAnalyticsDatasource {
        return FirebaseAnalyticsDatasourceImp(mActivity)
    }

    @ActivityScope
    @Provides
    fun provideRequestResetPasswordRouter(): ResetPasswordRouter {
        return ResetPasswordRouterImp(mActivity)
    }
}