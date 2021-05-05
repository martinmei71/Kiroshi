package com.wappiter.app.module.login

import com.wappiter.app.infrastucture.di.ActivityScope
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [LoginModule::class])
interface LoginComponent {
    fun inject(activity: LoginActivity)

}