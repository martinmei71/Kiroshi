package com.wappiter.app.module.myorders

import com.wappiter.app.infrastucture.di.ActivityScope
import com.wappiter.app.module.restaurantdetail.RestaurantDetailActivity
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [MyOrdersModule::class])

interface MyOrdersComponent {

    fun inject(myOrdersActivity: MyOrdersActivity)
}