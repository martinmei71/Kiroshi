package com.wappiter.app.module.restaurantdetail

import com.wappiter.app.infrastucture.di.ActivityScope
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [RestaurantDetailModule::class])

interface RestaurantDetailComponent {

    fun inject(restaurantDetailActivity: RestaurantDetailActivity)
}