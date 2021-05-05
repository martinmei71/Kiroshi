package com.wappiter.app.module.splash.router

import android.app.Activity
import com.wappiter.app.base.BaseRouterImp
import com.wappiter.app.module.termsandconditions.TermsAndConditionsActivity

class SplashRouterImp(activity: Activity) : BaseRouterImp(activity), SplashRouter {

    override fun goToTermsAndConditons() {
        mActivity.startActivity(TermsAndConditionsActivity.newIntent(mActivity))
        mActivity.finish()
    }

    override fun goToTermsAndConditonsFromDeeplink(restaurantId: String) {
        mActivity.startActivity(TermsAndConditionsActivity.newIntentFromDeeplink(mActivity, restaurantId))
        mActivity.finish()
    }
}


