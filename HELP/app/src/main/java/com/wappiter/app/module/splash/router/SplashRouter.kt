package com.wappiter.app.module.splash.router

import com.wappiter.app.base.BaseRouter

interface SplashRouter : BaseRouter {

    fun goToTermsAndConditons()

    fun goToTermsAndConditonsFromDeeplink(restaurantId: String)
}