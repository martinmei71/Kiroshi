package com.wappiter.app.module.restaurantdetail.presenter

import com.wappiter.app.presentation.base.BaseVP
import me.panavtec.threaddecoratedview.views.qualifiers.ThreadDecoratedView

@ThreadDecoratedView
interface RestaurantDetailView : BaseVP.View {

    fun initWebview(url: String, userApiKey: String? = null)

    fun showErrorNotUserLoggedToGoMyOrders()
}