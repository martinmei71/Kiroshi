package com.wappiter.app.module.main.presenter

import com.wappiter.app.presentation.base.BaseVP

interface MainPresenter : BaseVP.Presenter {

    fun startFlow()

    fun startFlowWithRestaurantId(restaurantId: String)
}