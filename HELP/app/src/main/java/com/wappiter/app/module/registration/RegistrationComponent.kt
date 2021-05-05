package com.wappiter.app.module.registration

import com.wappiter.app.infrastucture.di.ActivityScope
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [RegistrationModule::class])
interface RegistrationComponent {
    fun inject(activity: RegistrationActivity)
}