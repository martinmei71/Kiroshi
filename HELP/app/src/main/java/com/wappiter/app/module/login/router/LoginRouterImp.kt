package com.wappiter.app.module.login.router

import android.app.Activity
import com.wappiter.app.base.BaseRouterImp
import com.wappiter.app.module.profile.EditProfileActivity
import com.wappiter.app.module.registration.RegistrationActivity
import com.wappiter.app.module.resetpassword.ResetPasswordActivity

class LoginRouterImp(activity: Activity) : BaseRouterImp(activity), LoginRouter {

    override fun goToNextActivity(restaurantId: String) {
        if (restaurantId.isEmpty()) {
            goToMain()
        } else {
            goToMainFromDeeplink(restaurantId)
        }
    }

    override fun goToRegister() {
        mActivity.startActivity(RegistrationActivity.newIntent(mActivity))
    }

    override fun goToResetPassword() {
        mActivity.startActivity(ResetPasswordActivity.newIntent(mActivity))
    }

    override fun goToCompleteUserData() {
        mActivity.startActivity(EditProfileActivity.newIntent(mActivity))
    }
}