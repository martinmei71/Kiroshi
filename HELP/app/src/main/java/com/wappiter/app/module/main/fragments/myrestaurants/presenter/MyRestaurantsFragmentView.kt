package com.wappiter.app.module.main.fragments.myrestaurants.presenter

import com.wappiter.app.presentation.base.BaseVP
import me.panavtec.threaddecoratedview.views.qualifiers.ThreadDecoratedView

@ThreadDecoratedView
interface MyRestaurantsFragmentView : BaseVP.View {

    fun showLoginInfoDialog()

    fun showEmptyListView()

    fun hideEmptyListView()

    fun showMyRestaurantsWebview()

    fun hideMyRestaurantsWebview()

    fun setupMyRestaurantsWebview(restaurantsUrl: String, userApiKey: String)

    fun selectScannerTab()
}