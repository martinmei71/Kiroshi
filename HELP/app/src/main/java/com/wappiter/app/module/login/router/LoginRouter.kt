package com.wappiter.app.module.login.router

import com.wappiter.app.base.BaseRouter

interface LoginRouter : BaseRouter {

    fun goToNextActivity(restaurantId: String)

    fun goToRegister()

    fun goToResetPassword()

    fun goToCompleteUserData()
}