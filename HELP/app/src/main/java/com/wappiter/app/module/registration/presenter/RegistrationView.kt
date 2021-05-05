package com.wappiter.app.module.registration.presenter

import com.wappiter.app.presentation.base.BaseVP
import me.panavtec.threaddecoratedview.views.qualifiers.ThreadDecoratedView

@ThreadDecoratedView
interface RegistrationView : BaseVP.View {

    fun initWebview(url: String)
}