package com.wappiter.app.module.resetpassword.presenter

import com.wappiter.app.presentation.base.BaseVP

interface ResetPasswordPresenter : BaseVP.Presenter {

    fun startFlow()

    fun didClickToolbarBackButton()
}