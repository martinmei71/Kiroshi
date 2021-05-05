package com.wappiter.app.module.webview.generic

import android.content.Context
import android.content.Intent
import android.net.http.SslError
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.webkit.*
import com.wappiter.app.BuildConfig
import com.wappiter.app.R
import com.wappiter.app.base.BaseActivity
import com.wappiter.app.constants.AppConstants
import com.wappiter.app.infrastucture.di.AppComponent
import com.wappiter.app.module.webview.generic.presenter.GenericWebviewPresenter
import com.wappiter.app.module.webview.generic.presenter.GenericWebviewView
import com.wappiter.app.presentation.base.BaseVP
import com.wappiter.app.util.analytics.ScreenNames
import com.wappiter.app.util.webview.WebviewUtils
import kotlinx.android.synthetic.main.activity_generic_webview.*
import kotlinx.android.synthetic.main.toolbar_with_back.*
import java.util.*
import javax.inject.Inject


class GenericWebviewActivity : BaseActivity(), GenericWebviewView {

    private val TAG = GenericWebviewActivity::class.toString()

    @Inject
    lateinit var mPresenter: GenericWebviewPresenter

    companion object {
        fun newIntent(context: Context, url: String): Intent {
            val intent = Intent(context, GenericWebviewActivity::class.java)
            intent.putExtra(AppConstants.INTENT_PARAM_URL, url)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generic_webview)
        mPresenter.attachView(this)
    }

    override fun detachView() {
        mPresenter.detachView()
    }

    override fun getActivityTag(): String {
        return TAG
    }

    override fun getAnalyticsName(): String {
        return ScreenNames.GENERIC_WEBVIEW
    }

    override fun getPresenter(): BaseVP.Presenter {
        return mPresenter
    }

    override fun makeInjection(appComponent: AppComponent?) {
        appComponent?.plus(GenericWebviewModule(this))?.inject(this)
    }

    override fun initWebview(url: String, userApiKey: String?) {
        val webViewClient: WebViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView,
                                        url: String) {
                if (BuildConfig.DEBUG) {
                    Log.d("GenericWebviewActivity", "onPageFinished -> $url")
                }
                super.onPageFinished(view, url)
            }

            override fun shouldOverrideUrlLoading(view: WebView,
                                                  url: String): Boolean {
                if (BuildConfig.DEBUG) {
                    Log.d("GenericWebviewActivity", "shouldOverrideUrlLoading -> $url")
                }
                view.loadUrl(url, WebviewUtils().getWebviewHeaders(userApiKey))
                return false
            }
        }
        generic_webview.webViewClient = webViewClient
        generic_webview.settings.javaScriptEnabled = true
        generic_webview.loadUrl(url, WebviewUtils().getWebviewHeaders(userApiKey))
    }

    override fun setUpView() {
        initToolbar()
        val url = intent.getStringExtra(AppConstants.INTENT_PARAM_URL)
        mPresenter.startFlow(url)
    }

    private fun initToolbar() {
        toolbar_back_button.setOnClickListener { mPresenter.didClickToolbarBackButton() }
    }
}