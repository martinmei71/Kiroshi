package com.wappiter.app.module.webview.generic.presenter

import com.wappiter.app.presentation.base.BaseVP

interface GenericWebviewPresenter : BaseVP.Presenter {

    fun startFlow(url: String)

    fun didClickToolbarBackButton()
}