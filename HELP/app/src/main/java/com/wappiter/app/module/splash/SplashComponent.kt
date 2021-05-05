package com.wappiter.app.module.splash

import com.wappiter.app.infrastucture.di.ActivityScope
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [SplashModule::class])

interface SplashComponent {

    fun inject(splashActivity: SplashActivity)
}