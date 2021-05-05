package com.wappiter.app.module.profile

import com.wappiter.app.infrastucture.di.ActivityScope
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [EditProfileModule::class])

interface EditProfileComponent {

    fun inject(editProfileActivity: EditProfileActivity)
}