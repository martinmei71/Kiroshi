package com.wappiter.app.module.main.fragments.settings.presenter

import com.wappiter.app.presentation.base.BaseVP

interface SettingsFragmentPresenter : BaseVP.Presenter {

    fun startFlow()

    fun didClickLoginButton()

    fun didClickMyProfileButton()

    fun didClickTermsAndConditionsButton()

    fun didClickWebButton()

    fun didClickLogoutButton()

    fun didOnInitSession()

    fun didClickRestaurantManagerButton()

    fun didClickRateAppButton()

}