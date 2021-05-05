package com.wappiter.app.module.main

import com.wappiter.app.infrastucture.di.ActivityScope
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [MainModule::class])

interface MainComponent {

    fun inject(mainActivity: MainActivity)
}