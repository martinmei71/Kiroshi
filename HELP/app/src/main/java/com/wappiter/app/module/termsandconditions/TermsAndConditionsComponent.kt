package com.wappiter.app.module.termsandconditions

import com.wappiter.app.infrastucture.di.ActivityScope
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [TermsAndConditionsModule::class])

interface TermsAndConditionsComponent {

    fun inject(termsAndConditionsActivity: TermsAndConditionsActivity)
}