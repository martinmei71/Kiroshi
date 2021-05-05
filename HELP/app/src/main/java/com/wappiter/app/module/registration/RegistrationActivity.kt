package com.wappiter.app.module.registration

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import com.wappiter.app.BuildConfig
import com.wappiter.app.R
import com.wappiter.app.base.BaseActivity
import com.wappiter.app.data.constants.environment.EnvironmentConstants
import com.wappiter.app.infrastucture.di.AppComponent
import com.wappiter.app.module.registration.presenter.RegistrationPresenter
import com.wappiter.app.module.registration.presenter.RegistrationView
import com.wappiter.app.presentation.base.BaseVP
import com.wappiter.app.util.analytics.ScreenNames
import kotlinx.android.synthetic.main.activity_registration.*
import kotlinx.android.synthetic.main.toolbar_with_back.*
import javax.inject.Inject

class RegistrationActivity : BaseActivity(), RegistrationView {

    private val TAG: String = RegistrationActivity::class.toString()

    @Inject
    lateinit var mPresenter: RegistrationPresenter

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, RegistrationActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        mPresenter.attachView(this)
    }

    override fun detachView() {
        mPresenter.detachView()
    }

    override fun getActivityTag(): String {
        return TAG
    }

    override fun getAnalyticsName(): String {
        return ScreenNames.REGISTRATION
    }

    override fun getPresenter(): BaseVP.Presenter {
        return mPresenter
    }

    override fun makeInjection(appComponent: AppComponent?) {
        appComponent?.plus(RegistrationModule(this))?.inject(this)
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
                    Log.d("RegistrationWebview", "onPageFinished -> $url")

                }
                super.onPageFinished(view, url)
            }

            override fun shouldOverrideUrlLoading(view: WebView,
                                                  url: String): Boolean {
                if (BuildConfig.DEBUG) {
                    Log.d("RegistrationWebview", "shouldOverrideUrlLoading -> $url")

                }
                return if (EnvironmentConstants.TERMS_AND_CONDITIONS_URL == url) {
                    mPresenter.goToUrl(url)
                    true
                } else {
                    view.loadUrl(url)
                    false
                }
            }
        }
        register_webview.webViewClient = webViewClient
        register_webview.settings.javaScriptEnabled = true
        register_webview.loadUrl(url)
    }

    private fun initToolbar() {
        toolbar_back_button.setOnClickListener { mPresenter.didClickToolbarBackButton() }
    }
}