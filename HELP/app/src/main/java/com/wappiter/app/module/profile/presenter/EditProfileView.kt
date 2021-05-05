package com.wappiter.app.module.profile.presenter

import com.wappiter.app.presentation.base.BaseVP
import me.panavtec.threaddecoratedview.views.qualifiers.ThreadDecoratedView

@ThreadDecoratedView
interface EditProfileView : BaseVP.View {

    fun initWebview(url: String, userApiKey: String? = null)
}