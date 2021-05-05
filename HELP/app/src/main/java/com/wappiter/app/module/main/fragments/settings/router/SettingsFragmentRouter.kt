package com.wappiter.app.module.main.fragments.settings.router

import com.wappiter.app.base.BaseRouter

interface SettingsFragmentRouter : BaseRouter {

    fun goToExternalURL(url: String)
}