package com.wappiter.app.module.main.fragments.settings.router

import android.app.Activity
import android.content.Intent
import android.net.Uri
import com.wappiter.app.base.BaseRouterImp


class SettingsFragmentRouterImp(activity: Activity) : BaseRouterImp(activity), SettingsFragmentRouter {

    override fun goToExternalURL(url: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        mActivity.startActivity(intent)
    }
}


