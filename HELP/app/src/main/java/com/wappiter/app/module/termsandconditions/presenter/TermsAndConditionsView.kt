package com.wappiter.app.module.termsandconditions.presenter

import com.wappiter.app.presentation.base.BaseVP
import me.panavtec.threaddecoratedview.views.qualifiers.ThreadDecoratedView

@ThreadDecoratedView
interface TermsAndConditionsView : BaseVP.View {

    fun showTermsAndConditionsView()
}