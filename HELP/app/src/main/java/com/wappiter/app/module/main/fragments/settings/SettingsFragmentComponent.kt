package com.wappiter.app.module.main.fragments.settings

import com.wappiter.app.infrastucture.di.FragmentScope
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [SettingsFragmentModule::class])

interface SettingsFragmentComponent {

    fun inject(settingsFragment: SettingsFragment)
}