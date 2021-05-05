package com.wappiter.app.base

/**
 * Created by Javi on 11/12/2019.
 */
interface BaseRouter {

    fun goToMain()

    fun goToMainFromDeeplink(restaurantId: String)

    fun goToLogin(finishCurrentActivity: Boolean = true)

    fun goToBack()

    fun goToGenericWebview(url: String)

    fun goToRestaurantDetail(restaurantId: String)
}