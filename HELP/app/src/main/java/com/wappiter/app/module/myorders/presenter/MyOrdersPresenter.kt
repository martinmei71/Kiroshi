package com.wappiter.app.module.myorders.presenter

import com.wappiter.app.presentation.base.BaseVP

interface MyOrdersPresenter : BaseVP.Presenter {

    fun startFlow(restaurantId: String)

    fun didClickToolbarBackButton()

    fun didClickCloseErrorMessage()

    fun didLaunchSwipeRefresh()
}