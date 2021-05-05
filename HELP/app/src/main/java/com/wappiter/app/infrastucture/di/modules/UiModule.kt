package com.wappiter.app.infrastucture.di.modules

import androidx.annotation.UiThread
import com.wappiter.app.infrastucture.di.qualifiers.BackThread
import com.wappiter.app.infrastucture.di.qualifiers.SameThread
import com.wappiter.app.infrastucture.domain.outputs.BackThreadSpec
import com.wappiter.app.infrastucture.domain.outputs.MainThreadSpec
import com.wappiter.app.infrastucture.domain.outputs.SameThreadSpec
import com.wappiter.app.presentation.base.CustomViewInjector
import com.wappiter.app.presentation.base.CustomViewInjectorImp
import dagger.Module
import dagger.Provides
import me.panavtec.threaddecoratedview.views.ThreadSpec
import javax.inject.Singleton

@Module
class UiModule {
    @Provides
    @Singleton
    @UiThread
    fun provideMainThread(): ThreadSpec {
        return MainThreadSpec()
    }

    @Provides
    @Singleton
    @SameThread
    fun provideSameThread(): ThreadSpec {
        return SameThreadSpec()
    }

    @Provides
    @Singleton
    @BackThread
    fun provideBackThread(): ThreadSpec {
        return BackThreadSpec()
    }

    @Provides
    @Singleton
    fun provideViewInjector(imp: CustomViewInjectorImp): CustomViewInjector {
        return imp
    }

    @Provides
    @Singleton
    fun providesViewInjectorImp(@UiThread threadSpec: ThreadSpec): CustomViewInjectorImp {
        return CustomViewInjectorImp(threadSpec)
    }
}