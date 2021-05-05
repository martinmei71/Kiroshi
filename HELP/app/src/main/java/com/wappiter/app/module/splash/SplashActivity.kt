package com.wappiter.app.module.splash

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.afollestad.materialdialogs.MaterialDialog
import com.wappiter.app.BuildConfig
import com.wappiter.app.R
import com.wappiter.app.base.BaseActivity
import com.wappiter.app.constants.AppConstants
import com.wappiter.app.infrastucture.di.AppComponent
import com.wappiter.app.module.splash.presenter.AppSessionInput
import com.wappiter.app.module.splash.presenter.SplashPresenter
import com.wappiter.app.module.splash.presenter.SplashView
import com.wappiter.app.presentation.base.BaseVP
import com.wappiter.app.util.NetworkUtils
import com.wappiter.app.util.SystemUtils
import com.wappiter.app.util.analytics.ScreenNames
import io.branch.referral.Branch
import org.json.JSONException
import javax.inject.Inject

class SplashActivity : BaseActivity(), SplashView {

    private val TAG = SplashActivity::class.toString()
    private val SPLASH_DISPLAY_LENGTH: Long = 1500

    private var restaurantId = ""
    private var isUpdateAvailableDialogVisible = false

    @Inject
    lateinit var mPresenter: SplashPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        mPresenter.attachView(this)
    }

    override fun detachView() {
        mPresenter.detachView()
    }

    override fun onBackPressed() {
        if (isUpdateAvailableDialogVisible) {
            return
        }
        super.onBackPressed()
    }

    override fun getActivityTag(): String {
        return TAG
    }

    override fun getAnalyticsName(): String {
        return ScreenNames.SPLASH
    }

    override fun getPresenter(): BaseVP.Presenter {
        return mPresenter
    }

    override fun makeInjection(appComponent: AppComponent?) {
        appComponent?.plus(SplashModule(this))?.inject(this)
    }

    override fun setUpView() {
        mPresenter.startFlow()
    }

    override fun onActionsDone() {
        Handler().postDelayed(this.initBranchIO(), SPLASH_DISPLAY_LENGTH)
    }

    override fun showDialogExpireVersion() {
        if (!isFinishing && !isDestroyed) {
            if (!isUpdateAvailableDialogVisible) {
                isUpdateAvailableDialogVisible = true
                MaterialDialog(this).show {
                    title(R.string.app_name)
                    message(R.string.expired_version_message)
                    positiveButton(R.string.expired_version_button) {
                        isUpdateAvailableDialogVisible = false
                        mPresenter.didClickGoToStore()
                    }
                    cancelOnTouchOutside(false)
                }
            }
        }
    }

    override fun goToStore() {
        val appPackageName = SystemUtils
                .packageName(this) // getPackageName() from Context or Activity
        try {
            startActivity(Intent(Intent.ACTION_VIEW,
                    Uri.parse("market://details?id=$appPackageName")))
        } catch (anfe: ActivityNotFoundException) {
            startActivity(Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://play.google.com/store/apps/details?id=$appPackageName")))
        }
    }

    private fun getAppSessionInput(): AppSessionInput {
        val appSessionInput = AppSessionInput()
        appSessionInput.appVersionCode = SystemUtils.appCode
        appSessionInput.appVersionName = SystemUtils.appVersion
        appSessionInput.networkType = NetworkUtils.getNetworkTypeParam(this)
        appSessionInput.ssidWifi = NetworkUtils.getCurrentWiFiSSID(this) ?: ""
        appSessionInput.deviceUniqueIdentifier = SystemUtils.uniqueIdentifier
        appSessionInput.mobileOperatingSystemName = SystemUtils.operatingSystem
        appSessionInput.mobileOperatingSystemName = SystemUtils.operatingSystem
        appSessionInput.mobileOperatingSystemVersionName = SystemUtils.androidReleaseNumber
        appSessionInput.deviceManufacturerName = SystemUtils.deviceManufacturer
        appSessionInput.deviceModelName = SystemUtils.deviceModel
        return appSessionInput
    }

    private fun initBranchIO() = Runnable {
        Branch.getInstance().initSession({ referringParams, error ->
            if (error == null) {
                if (BuildConfig.DEBUG) {
                    Log.i("BRANCH SDK", referringParams.toString())
                }
                try {
                    val branchIOParams = Branch.getInstance().latestReferringParams
                    if (branchIOParams.getString("+clicked_branch_link") == "true") {
                        if (BuildConfig.DEBUG) {
                            Log.i("BRANCH SDK Params", branchIOParams.toString())
                        }
                        try {
                            val type = branchIOParams.getString(AppConstants.DEEPLINK_TYPE_PARAM)
                            if (AppConstants.DEEPLINK_TYPE_RESTAURANT_DETAILS_PARAM.equals(type)) {
                                restaurantId = branchIOParams.getString(AppConstants.DEEPLINK_RESTAURANT_ID_PARAM)
                            }
                        } catch (e: JSONException) {
                            e.printStackTrace()
                        }
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            } else {
                if (BuildConfig.DEBUG) {
                    Log.i("BRANCH SDK", error.message)
                }
            }

            mPresenter.initApp(getAppSessionInput(), restaurantId)
        }, this.intent.data, this)
    }
}