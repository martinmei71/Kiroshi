package com.wappiter.app.module.restaurantdetail.presenter

import com.wappiter.app.presentation.base.BaseVP

interface RestaurantDetailPresenter : BaseVP.Presenter {

    fun startFlow(restaurantId: String)

    fun didClickToolbarBackButton()

    fun didClickMyOrdersButton()

    fun interceptOpenLogin()

    fun didOnInitSession()

    fun didClickGoToLogin()

}