package com.wappiter.app.module.webview.generic.presenter

import com.wappiter.app.presentation.base.BaseVP
import me.panavtec.threaddecoratedview.views.qualifiers.ThreadDecoratedView

@ThreadDecoratedView
interface GenericWebviewView : BaseVP.View {

    fun initWebview(url: String, userApiKey: String? = null)
}