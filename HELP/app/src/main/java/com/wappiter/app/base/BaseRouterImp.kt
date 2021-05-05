package com.wappiter.app.base

import android.app.Activity
import com.wappiter.app.module.login.LoginActivity
import com.wappiter.app.module.main.MainActivity
import com.wappiter.app.module.restaurantdetail.RestaurantDetailActivity
import com.wappiter.app.module.webview.generic.GenericWebviewActivity

/**
 * Created by Javi on 11/12/2019.
 */
open class BaseRouterImp(protected var mActivity: Activity) : BaseRouter {

    override fun goToMain() {
        mActivity.startActivity(MainActivity.newIntent(mActivity))
        mActivity.finish()
    }

    override fun goToMainFromDeeplink(restaurantId: String) {
        mActivity.startActivity(MainActivity.newIntentFromDeeplink(mActivity, restaurantId))
        mActivity.finish()
    }

    override fun goToLogin(finishCurrentActivity: Boolean) {
        mActivity.startActivity(LoginActivity.newIntent(mActivity))
        if (finishCurrentActivity) {
            mActivity.finish()
        }
    }

    override fun goToBack() {
        mActivity.finish()
    }

    override fun goToGenericWebview(url: String) {
        mActivity.startActivity(GenericWebviewActivity.newIntent(mActivity, url))
    }

    override fun goToRestaurantDetail(restaurantId: String) {
        mActivity.startActivity(RestaurantDetailActivity.newIntent(mActivity, restaurantId))
    }
}