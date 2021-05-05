package com.wappiter.app.module.main.fragments.settings.presenter

import com.wappiter.app.presentation.base.BaseVP
import me.panavtec.threaddecoratedview.views.qualifiers.ThreadDecoratedView

@ThreadDecoratedView
interface SettingsFragmentView : BaseVP.View {

    fun showLoginButton()

    fun showMyProfileButton()

    fun showLogoutButton()

    fun hideLoginButton()

    fun hideMyProfileButton()

    fun hideLogoutButton()

    fun goToStore()

}