package com.wappiter.app.infrastucture.di.modules

import com.wappiter.app.infrastucture.errorhandling.InteractorErrorHandlerImpl
import com.wappiter.data.apppreferences.AppPreferencesDatasourceImp
import com.wappiter.data.apppreferences.PreferencesHelper
import com.wappiter.infrastructure.datasource.AppPreferencesDatasource
import com.wappiter.infrastructure.interactor.InteractorErrorHandler
import com.wappiter.infrastructure.interactor.InteractorErrorMessages
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by porta on 18/05/17.
 */
@Module
class AppInfrastructureModule {
    @Singleton
    @Provides
    fun provideAppPreferencesDatasource(preferencesHelper: PreferencesHelper): AppPreferencesDatasource {
        return AppPreferencesDatasourceImp(preferencesHelper)
    }

    @Provides
    @Singleton
    fun provideInteractorErrorHandler(interactorErrorMessages: InteractorErrorMessages): InteractorErrorHandler {
        return InteractorErrorHandlerImpl()
    }
}