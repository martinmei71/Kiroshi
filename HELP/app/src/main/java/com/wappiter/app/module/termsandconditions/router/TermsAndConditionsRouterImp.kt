package com.wappiter.app.module.termsandconditions.router

import android.app.Activity
import com.wappiter.app.base.BaseRouterImp
import com.wappiter.app.module.main.MainActivity

class TermsAndConditionsRouterImp(activity: Activity) : BaseRouterImp(activity), TermsAndConditionsRouter {

    override fun goToLoginFromDeeplink(restaurantId: String) {
        mActivity.startActivity(MainActivity.newIntentFromDeeplink(mActivity, restaurantId))
        mActivity.finish()
    }
}


