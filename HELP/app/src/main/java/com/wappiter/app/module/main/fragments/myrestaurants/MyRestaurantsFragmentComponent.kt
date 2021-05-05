package com.wappiter.app.module.main.fragments.myrestaurants

import com.wappiter.app.infrastucture.di.FragmentScope
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [MyRestaurantsFragmentModule::class])

interface MyRestaurantsFragmentComponent {

    fun inject(myRestaurantsFragment: MyRestaurantsFragment)
}