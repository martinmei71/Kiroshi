package com.wappiter.app.module.splash.presenter

import com.wappiter.app.presentation.base.BaseVP
import me.panavtec.threaddecoratedview.views.qualifiers.ThreadDecoratedView

@ThreadDecoratedView
interface SplashView : BaseVP.View {

    fun onActionsDone()

    fun showDialogExpireVersion()

    fun goToStore()

}