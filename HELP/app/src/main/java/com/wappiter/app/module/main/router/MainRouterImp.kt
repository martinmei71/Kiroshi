package com.wappiter.app.module.main.router

import android.app.Activity
import com.wappiter.app.base.BaseRouterImp
import com.wappiter.app.module.profile.EditProfileActivity

class MainRouterImp(activity: Activity) : BaseRouterImp(activity), MainRouter {

    override fun goToCompleteUserData() {
        mActivity.startActivity(EditProfileActivity.newIntent(mActivity))
    }
}