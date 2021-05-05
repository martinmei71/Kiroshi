package com.wappiter.app.module.termsandconditions.router

import com.wappiter.app.base.BaseRouter

interface TermsAndConditionsRouter : BaseRouter {

    fun goToLoginFromDeeplink(restaurantId: String)
}