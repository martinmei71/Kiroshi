package com.wappiter.app.module.resetpassword

import com.wappiter.app.infrastucture.di.ActivityScope
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [ResetPasswordModule::class])
interface ResetPasswordComponent {
    fun inject(activityKT: ResetPasswordActivity)
}