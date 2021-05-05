package com.wappiter.app.module.profile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import com.wappiter.app.BuildConfig
import com.wappiter.app.R
import com.wappiter.app.base.BaseActivity
import com.wappiter.app.constants.AppConstants
import com.wappiter.app.infrastucture.di.AppComponent
import com.wappiter.app.module.profile.presenter.EditProfilePresenter
import com.wappiter.app.module.profile.presenter.EditProfileView
import com.wappiter.app.module.webview.generic.presenter.GenericWebviewPresenter
import com.wappiter.app.presentation.base.BaseVP
import com.wappiter.app.util.analytics.ScreenNames
import com.wappiter.app.util.webview.WebviewUtils
import kotlinx.android.synthetic.main.activity_edit_profile.*
import kotlinx.android.synthetic.main.toolbar_with_back.*
import javax.inject.Inject


class EditProfileActivity : BaseActivity(), EditProfileView {

    private val TAG = EditProfileActivity::class.toString()

    @Inject
    lateinit var mPresenter: EditProfilePresenter

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, EditProfileActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)
        mPresenter.attachView(this)
    }

    override fun onBackPressed() {
        mPresenter.checkIfUserCompletedTheData()
        return
    }

    override fun detachView() {
        mPresenter.detachView()
    }

    override fun getActivityTag(): String {
        return TAG
    }

    override fun getAnalyticsName(): String {
        return ScreenNames.EDIT_PROFILE
    }

    override fun getPresenter(): BaseVP.Presenter {
        return mPresenter
    }

    override fun makeInjection(appComponent: AppComponent?) {
        appComponent?.plus(EditProfileModule(this))?.inject(this)
    }

    override fun initWebview(url: String, userApiKey: String?) {
        val webViewClient: WebViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView,
                                        url: String) {
                if (BuildConfig.DEBUG) {
                    Log.d("EditProfileActivity", "onPageFinished -> $url")
                }
                super.onPageFinished(view, url)
            }

            override fun shouldOverrideUrlLoading(view: WebView,
                                                  url: String): Boolean {
                if (BuildConfig.DEBUG) {
                    Log.d("EditProfileActivity", "shouldOverrideUrlLoading -> $url")
                }
                view.loadUrl(url, WebviewUtils().getWebviewHeaders(userApiKey))
                return false
            }
        }
        edit_profile_webview.webViewClient = webViewClient
        edit_profile_webview.settings.javaScriptEnabled = true
        edit_profile_webview.loadUrl(url, WebviewUtils().getWebviewHeaders(userApiKey))
    }

    override fun setUpView() {
        initToolbar()
        mPresenter.startFlow()
    }

    private fun initToolbar() {
        toolbar_back_button.setOnClickListener { mPresenter.didClickToolbarBackButton() }
    }
}