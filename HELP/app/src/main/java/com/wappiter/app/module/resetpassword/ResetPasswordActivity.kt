package com.wappiter.app.module.resetpassword

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import com.wappiter.app.BuildConfig
import com.wappiter.app.R
import com.wappiter.app.base.BaseActivity
import com.wappiter.app.infrastucture.di.AppComponent
import com.wappiter.app.module.resetpassword.presenter.ResetPasswordPresenter
import com.wappiter.app.module.resetpassword.presenter.ResetPasswordView
import com.wappiter.app.presentation.base.BaseVP
import com.wappiter.app.util.analytics.ScreenNames
import kotlinx.android.synthetic.main.activity_request_reset_password_code.*
import kotlinx.android.synthetic.main.toolbar_with_back.*
import javax.inject.Inject

class ResetPasswordActivity : BaseActivity(), ResetPasswordView {

    val TAG: String = ResetPasswordActivity::class.toString()

    @Inject
    lateinit var mPresenter: ResetPasswordPresenter

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, ResetPasswordActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_request_reset_password_code)
        mPresenter.attachView(this)
    }

    override fun getActivityTag(): String {
        return TAG
    }

    override fun getAnalyticsName(): String {
        return ScreenNames.REQUEST_RESET_PASSWORD
    }

    override fun getPresenter(): BaseVP.Presenter {
        return mPresenter
    }

    override fun makeInjection(appComponent: AppComponent?) {
        appComponent?.plus(ResetPasswordModule(this))?.inject(this)
    }

    override fun detachView() {
        mPresenter.detachView()
    }

    override fun setUpView() {
        initToolbar()
        mPresenter.startFlow()
    }

    override fun initWebview(url: String) {
        val webViewClient: WebViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView,
                                        url: String) {
                if (BuildConfig.DEBUG) {
                    Log.d("ResetPasswordWebview", "onPageFinished -> $url")
                }
                super.onPageFinished(view, url)
            }

            override fun shouldOverrideUrlLoading(view: WebView,
                                                  url: String): Boolean {
                if (BuildConfig.DEBUG) {
                    Log.d("ResetPasswordWebview", "shouldOverrideUrlLoading -> $url")
                }
                view.loadUrl(url)
                return false
            }
        }
        reset_password_webview.webViewClient = webViewClient
        reset_password_webview.settings.javaScriptEnabled = true
        reset_password_webview.loadUrl(url)
    }

    private fun initToolbar() {
        toolbar_back_button.setOnClickListener { mPresenter.didClickToolbarBackButton() }
    }
}