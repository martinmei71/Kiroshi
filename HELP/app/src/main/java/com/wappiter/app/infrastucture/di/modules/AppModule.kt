package com.wappiter.app.infrastucture.di.modules

import com.wappiter.app.GlobalApplication
import com.wappiter.app.infrastucture.errorhandling.InteractorErrorMessagesImpl
import com.wappiter.app.infrastucture.resources.ResourcesAccessorImpl
import com.wappiter.data.apppreferences.PreferencesHelper
import com.wappiter.infrastructure.accesor.ResourcesAccessor
import com.wappiter.infrastructure.interactor.InteractorErrorMessages
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by porta on 18/05/17.
 */
@Module
class AppModule(private val mApplication: GlobalApplication) {
    @Provides
    @Singleton
    fun providesApplication(): GlobalApplication {
        return mApplication
    }

    @Provides
    @Singleton
    fun providePreferencesHelper(): PreferencesHelper {
        return PreferencesHelper(mApplication)
    }

    @Provides
    @Singleton
    fun provideResourcesAccessor(): ResourcesAccessor {
        return ResourcesAccessorImpl(mApplication)
    }

    @Provides
    @Singleton
    fun provideInteractorErrorMessages(): InteractorErrorMessages {
        return InteractorErrorMessagesImpl(mApplication)
    }
}