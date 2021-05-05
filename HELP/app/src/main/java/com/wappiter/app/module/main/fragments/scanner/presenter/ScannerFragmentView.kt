package com.wappiter.app.module.main.fragments.scanner.presenter

import com.wappiter.app.presentation.base.BaseVP
import me.panavtec.threaddecoratedview.views.qualifiers.ThreadDecoratedView

@ThreadDecoratedView
interface ScannerFragmentView : BaseVP.View {

    fun setScannerView()

    fun stopScanner()

    fun checkCameraPermission()

    fun playSound()

    fun resumeCameraPreview()

    fun showCameraPermissionInfoView()

    fun openAppPermissionSettings()

    fun showQRError()
}