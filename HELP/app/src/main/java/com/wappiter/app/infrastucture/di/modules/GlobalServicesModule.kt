package com.wappiter.app.infrastucture.di.modules

import com.wappiter.data.ApiRetrofit
import com.wappiter.data.apppreferences.PreferencesHelper
import com.wappiter.data.base.mapper.ApiVoid
import com.wappiter.data.base.mapper.VoidApiModelMapper
import com.wappiter.data.user.logout.LogoutDatasourceImp
import com.wappiter.data.user.session.UserSessionLocalDatasourceImp
import com.wappiter.domain.base.Mapper
import com.wappiter.domain.base.VoidModel
import com.wappiter.domain.preferences.service.PreferencesService
import com.wappiter.domain.preferences.service.PreferencesServiceImpl
import com.wappiter.domain.user.logout.data.LogoutDatasource
import com.wappiter.domain.user.logout.interactor.LogoutInteractor
import com.wappiter.domain.user.logout.service.LogoutService
import com.wappiter.domain.user.logout.service.LogoutServiceImp
import com.wappiter.domain.user.session.UserSessionLocalDatasource
import com.wappiter.domain.user.session.service.UserSessionService
import com.wappiter.domain.user.session.service.UserSessionServiceImp
import com.wappiter.infrastructure.datasource.AppPreferencesDatasource
import com.wappiter.infrastructure.interactor.InteractorErrorHandler
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by porta on 18/05/17.
 */
@Module
class GlobalServicesModule {
    @Singleton
    @Provides
    fun provideLogoutInteractor(logoutService: LogoutService,
                                interactorErrorHandler: InteractorErrorHandler): LogoutInteractor {
        return LogoutInteractor(logoutService, interactorErrorHandler)
    }

    @Singleton
    @Provides
    fun providePreferencesService(appPreferencesDatasource: AppPreferencesDatasource): PreferencesService {
        return PreferencesServiceImpl(appPreferencesDatasource)
    }

    @Singleton
    @Provides
    fun provideLogoutService(userSessionLocalDatasource: UserSessionLocalDatasource,
                             appPreferencesDatasource: AppPreferencesDatasource,
                             logoutDatasource: LogoutDatasource): LogoutService {
        return LogoutServiceImp(userSessionLocalDatasource, appPreferencesDatasource, logoutDatasource)
    }

    @Singleton
    @Provides
    fun provideUserSessionService(userSessionLocalDatasource: UserSessionLocalDatasource): UserSessionService {
        return UserSessionServiceImp(userSessionLocalDatasource)
    }

    @Singleton
    @Provides
    fun provideUserDatasource(preferencesHelper: PreferencesHelper): UserSessionLocalDatasource {
        return UserSessionLocalDatasourceImp(preferencesHelper)
    }

    @Singleton
    @Provides
    fun provideLogoutDatasource(apiRetrofit: ApiRetrofit, mapper: Mapper<ApiVoid, VoidModel>): LogoutDatasource {
        return LogoutDatasourceImp(apiRetrofit, mapper)
    }
}