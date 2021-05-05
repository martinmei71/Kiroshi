package com.wappiter.app.module.resetpassword.presenter

import com.wappiter.app.presentation.base.BaseVP
import me.panavtec.threaddecoratedview.views.qualifiers.ThreadDecoratedView

@ThreadDecoratedView
interface ResetPasswordView : BaseVP.View {

    fun initWebview(url: String)
}