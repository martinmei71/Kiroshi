package com.wappiter.app.module.termsandconditions

import android.app.Activity
import com.wappiter.app.infrastucture.di.ActivityScope
import com.wappiter.app.module.termsandconditions.presenter.TermsAndConditionsPresenter
import com.wappiter.app.module.termsandconditions.presenter.TermsAndConditionsPresenterImp
import com.wappiter.app.module.termsandconditions.router.TermsAndConditionsRouter
import com.wappiter.app.module.termsandconditions.router.TermsAndConditionsRouterImp
import com.wappiter.app.presentation.base.CustomViewInjector
import com.wappiter.app.presentation.base.invoker.InteractorInvoker
import com.wappiter.data.apppreferences.PreferencesHelper
import com.wappiter.data.firebase.FirebaseAnalyticsDatasourceImp
import com.wappiter.data.user.termsandconditions.TermsAndConditionsDatasourceImp
import com.wappiter.domain.firebase.FirebaseAnalyticsDatasource
import com.wappiter.domain.user.logout.interactor.LogoutInteractor
import com.wappiter.domain.user.termsandconditions.data.TermsAndConditionsDatasource
import com.wappiter.domain.user.termsandconditions.interactor.GetAcceptedTermsAndConditionsInteractor
import com.wappiter.domain.user.termsandconditions.interactor.SetAcceptedTermsAndConditionsInteractor
import com.wappiter.domain.user.termsandconditions.service.TermsAndConditionsService
import com.wappiter.domain.user.termsandconditions.service.TermsAndConditionsServiceImp
import com.wappiter.infrastructure.accesor.ResourcesAccessor
import com.wappiter.infrastructure.interactor.InteractorErrorHandler
import dagger.Module
import dagger.Provides

@Module
open class TermsAndConditionsModule(activity: Activity) {

    val mActivity = activity

    @ActivityScope
    @Provides
    open fun providePresenter(interactorInvoker: InteractorInvoker, getAcceptedTermsAndConditionsInteractor: GetAcceptedTermsAndConditionsInteractor, setAcceptedTermsAndConditionsInteractor: SetAcceptedTermsAndConditionsInteractor, logoutInteractor: LogoutInteractor, resourcesAccessor: ResourcesAccessor, firebaseAnalyticsDatasource: FirebaseAnalyticsDatasource, router: TermsAndConditionsRouter, customViewInjector: CustomViewInjector): TermsAndConditionsPresenter {

        return TermsAndConditionsPresenterImp(interactorInvoker, getAcceptedTermsAndConditionsInteractor, setAcceptedTermsAndConditionsInteractor, logoutInteractor, resourcesAccessor, firebaseAnalyticsDatasource, router, customViewInjector)
    }

    @ActivityScope
    @Provides
    fun provideGetTermsAndConditionsInteractor(termsAndConditionsService: TermsAndConditionsService, interactorErrorHandler: InteractorErrorHandler): GetAcceptedTermsAndConditionsInteractor {
        return GetAcceptedTermsAndConditionsInteractor(termsAndConditionsService, interactorErrorHandler)
    }

    @ActivityScope
    @Provides
    fun provideSetTermsAndConditionsInteractor(termsAndConditionsService: TermsAndConditionsService, interactorErrorHandler: InteractorErrorHandler): SetAcceptedTermsAndConditionsInteractor {
        return SetAcceptedTermsAndConditionsInteractor(termsAndConditionsService, interactorErrorHandler)
    }

    @ActivityScope
    @Provides
    fun provideTermsAndConditionsService(termsAndConditionsDatasource: TermsAndConditionsDatasource): TermsAndConditionsService {
        return TermsAndConditionsServiceImp(termsAndConditionsDatasource)
    }

    @ActivityScope
    @Provides
    fun provideTermsAndConditionsDatasource(preferencesHelper: PreferencesHelper?): TermsAndConditionsDatasource {
        return TermsAndConditionsDatasourceImp(preferencesHelper!!)
    }

    @ActivityScope
    @Provides
    fun provideTermsAndConditionsRouter(): TermsAndConditionsRouter {
        return TermsAndConditionsRouterImp(mActivity)
    }

    @ActivityScope
    @Provides
    fun provideFirebaseAnalyticsDatasource(): FirebaseAnalyticsDatasource {
        return FirebaseAnalyticsDatasourceImp(mActivity)
    }
}