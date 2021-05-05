package com.wappiter.app.module.myorders.presenter

import com.wappiter.app.presentation.base.BaseVP
import me.panavtec.threaddecoratedview.views.qualifiers.ThreadDecoratedView

@ThreadDecoratedView
interface MyOrdersView : BaseVP.View {

    fun initWebview(url: String, userApiKey: String? = null)

    fun showErrorOnGetUser()

    fun reloadWebview()
}