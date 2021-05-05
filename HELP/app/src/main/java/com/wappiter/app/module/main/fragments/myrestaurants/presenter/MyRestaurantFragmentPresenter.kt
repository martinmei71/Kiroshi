package com.wappiter.app.module.main.fragments.myrestaurants.presenter

import com.wappiter.app.presentation.base.BaseVP

interface MyRestaurantFragmentPresenter : BaseVP.Presenter {

    fun startFlow()

    fun didClickEmptyListButton()

    fun didClickRestaurant(url: String)

    fun didOnSelectMyRestaurantTab()

    fun didOnInitSession()

    fun didOnCloseSession()

    fun didOnAddFavouriteRestaurantEvent()

    fun didClickEnterOnWappiter()
}