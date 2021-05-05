package com.wappiter.app.module.main.fragments.settings

import androidx.fragment.app.FragmentActivity
import com.wappiter.app.infrastucture.di.FragmentScope
import com.wappiter.app.module.main.fragments.settings.presenter.SettingsFragmentPresenter
import com.wappiter.app.module.main.fragments.settings.presenter.SettingsFragmentPresenterImp
import com.wappiter.app.module.main.fragments.settings.router.SettingsFragmentRouter
import com.wappiter.app.module.main.fragments.settings.router.SettingsFragmentRouterImp
import com.wappiter.app.presentation.base.CustomViewInjector
import com.wappiter.app.presentation.base.invoker.InteractorInvoker
import com.wappiter.data.base.VoidApiModel
import com.wappiter.data.base.mapper.VoidApiModelMapper
import com.wappiter.data.firebase.FirebaseAnalyticsDatasourceImp
import com.wappiter.domain.base.Mapper
import com.wappiter.domain.base.VoidModel
import com.wappiter.domain.firebase.FirebaseAnalyticsDatasource
import com.wappiter.domain.user.logout.interactor.LogoutInteractor
import com.wappiter.domain.user.profile.interactor.GetUserLoggedInteractor
import com.wappiter.domain.user.session.service.UserSessionService
import com.wappiter.infrastructure.accesor.ResourcesAccessor
import com.wappiter.infrastructure.interactor.InteractorErrorHandler
import dagger.Module
import dagger.Provides

@Module
open class SettingsFragmentModule(activity: FragmentActivity) {

    val mActivity = activity

    @FragmentScope
    @Provides
    open fun providePresenter(interactorInvoker: InteractorInvoker, getUserLoggedInteractor: GetUserLoggedInteractor, logoutInteractor: LogoutInteractor, resourcesAccessor: ResourcesAccessor, firebaseAnalyticsDatasource: FirebaseAnalyticsDatasource, router: SettingsFragmentRouter, customViewInjector: CustomViewInjector): SettingsFragmentPresenter {
        return SettingsFragmentPresenterImp(interactorInvoker, getUserLoggedInteractor, logoutInteractor, resourcesAccessor, firebaseAnalyticsDatasource, router, customViewInjector)
    }

    @FragmentScope
    @Provides
    fun provideGetUserLoggedInteractor(userSessionService: UserSessionService, interactorErrorHandler: InteractorErrorHandler): GetUserLoggedInteractor {
        return GetUserLoggedInteractor(userSessionService, interactorErrorHandler)
    }

    @FragmentScope
    @Provides
    fun provideSplashRouter(): SettingsFragmentRouter {
        return SettingsFragmentRouterImp(mActivity)
    }

    @FragmentScope
    @Provides
    fun provideFirebaseAnalyticsDatasource(): FirebaseAnalyticsDatasource {
        return FirebaseAnalyticsDatasourceImp(mActivity)
    }
}