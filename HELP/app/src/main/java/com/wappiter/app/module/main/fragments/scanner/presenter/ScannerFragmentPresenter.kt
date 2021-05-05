package com.wappiter.app.module.main.fragments.scanner.presenter

import com.google.zxing.Result
import com.wappiter.app.presentation.base.BaseVP

interface ScannerFragmentPresenter : BaseVP.Presenter {

    fun didOnResume()

    fun didOnPause()

    fun didOnPermissionChecked()

    fun didOnPermissionDenied()

    fun didReadCode(result: Result)

    fun didCloseQRErrorDialog()

    fun didClickGoToSettings()

    fun didClickEnableCameraPermission()
}