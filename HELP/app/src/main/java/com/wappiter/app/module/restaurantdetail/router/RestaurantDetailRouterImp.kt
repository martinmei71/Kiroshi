package com.wappiter.app.module.restaurantdetail.router

import android.app.Activity
import com.wappiter.app.base.BaseRouterImp
import com.wappiter.app.module.myorders.MyOrdersActivity
import com.wappiter.app.module.termsandconditions.TermsAndConditionsActivity

class RestaurantDetailRouterImp(activity: Activity) : BaseRouterImp(activity), RestaurantDetailRouter {

    override fun goToMyOrders(restaurantId: String) {
        mActivity.startActivity(MyOrdersActivity.newIntent(mActivity, restaurantId))
    }
}


