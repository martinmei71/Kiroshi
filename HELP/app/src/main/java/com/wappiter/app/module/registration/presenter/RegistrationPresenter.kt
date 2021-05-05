package com.wappiter.app.module.registration.presenter

import com.wappiter.app.presentation.base.BaseVP

interface RegistrationPresenter : BaseVP.Presenter {

    fun startFlow()

    fun didClickToolbarBackButton()

    fun goToUrl(url: String)
}