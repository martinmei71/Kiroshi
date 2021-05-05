package com.wappiter.app.module.splash.presenter

import com.wappiter.app.presentation.base.BaseVP

interface SplashPresenter : BaseVP.Presenter {

    fun startFlow()

    fun initApp(appSessionInput: AppSessionInput, restaurantId: String)

    fun didClickGoToStore()
}