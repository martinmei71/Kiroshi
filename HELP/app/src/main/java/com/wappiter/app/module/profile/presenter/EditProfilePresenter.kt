package com.wappiter.app.module.profile.presenter

import com.wappiter.app.presentation.base.BaseVP

interface EditProfilePresenter : BaseVP.Presenter {

    fun startFlow()

    fun didClickToolbarBackButton()

    fun checkIfUserCompletedTheData()
}