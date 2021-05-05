package com.wappiter.app.module.restaurantdetail.router

import com.wappiter.app.base.BaseRouter

interface RestaurantDetailRouter : BaseRouter {

    fun goToMyOrders(restaurantId: String)

}