package com.wappiter.app.module.myorders

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.afollestad.materialdialogs.MaterialDialog
import com.wappiter.app.BuildConfig
import com.wappiter.app.R
import com.wappiter.app.base.BaseActivity
import com.wappiter.app.constants.AppConstants
import com.wappiter.app.infrastucture.di.AppComponent
import com.wappiter.app.module.myorders.presenter.MyOrdersPresenter
import com.wappiter.app.module.myorders.presenter.MyOrdersView
import com.wappiter.app.presentation.base.BaseVP
import com.wappiter.app.util.analytics.ScreenNames
import com.wappiter.app.util.webview.WebviewUtils
import kotlinx.android.synthetic.main.activity_my_orders.*
import kotlinx.android.synthetic.main.toolbar_with_back.*
import javax.inject.Inject

class MyOrdersActivity : BaseActivity(), MyOrdersView {

    private val TAG = MyOrdersActivity::class.toString()

    @Inject
    lateinit var mPresenter: MyOrdersPresenter

    companion object {
        fun newIntent(context: Context, restaurantId: String): Intent {
            val intent = Intent(context, MyOrdersActivity::class.java)
            intent.putExtra(AppConstants.INTENT_PARAM_RESTAURANT_ID, restaurantId)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_orders)
        mPresenter.attachView(this)
    }

    override fun detachView() {
        mPresenter.detachView()
    }

    override fun getActivityTag(): String {
        return TAG
    }

    override fun getAnalyticsName(): String {
        return ScreenNames.MY_ORDERS
    }

    override fun getPresenter(): BaseVP.Presenter {
        return mPresenter
    }

    override fun makeInjection(appComponent: AppComponent?) {
        appComponent?.plus(MyOrdersModule(this))?.inject(this)
    }

    override fun setUpView() {
        setupToolbar()
        setupSwipeRefresh()
        val restaurantId = intent.getStringExtra(AppConstants.INTENT_PARAM_RESTAURANT_ID)
        mPresenter.startFlow(restaurantId)
    }

    private fun setupSwipeRefresh() {
        swipeRefresh.setOnRefreshListener {
            mPresenter.didLaunchSwipeRefresh()
        }
        swipeRefresh.setColorSchemeResources(R.color.primary_color,
                R.color.primary_color,
                R.color.primary_color,
                R.color.primary_color)
    }

    override fun initWebview(url: String, userApiKey: String?) {
        val webViewClient: WebViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView,
                                        url: String) {
                if (BuildConfig.DEBUG) {
                    Log.d("MyOrdersWebview", "onPageFinished -> $url")

                }
                swipeRefresh.isRefreshing = false
                super.onPageFinished(view, url)
            }

            override fun shouldOverrideUrlLoading(view: WebView,
                                                  url: String): Boolean {
                if (BuildConfig.DEBUG) {
                    Log.d("MyOrdersWebview", "shouldOverrideUrlLoading -> $url")

                }
                view.loadUrl(url, WebviewUtils().getWebviewHeaders(userApiKey))
                return false
            }
        }
        my_orders_webview.webViewClient = webViewClient
        my_orders_webview.settings.javaScriptEnabled = true
        my_orders_webview.loadUrl(url, WebviewUtils().getWebviewHeaders(userApiKey))
    }

    override fun reloadWebview() {
        my_orders_webview.reload()
    }

    override fun showErrorOnGetUser() {
        if (!isFinishing && !isDestroyed) {
            MaterialDialog(this)
                    .title(R.string.app_name)
                    .message(R.string.error_generic)
                    .positiveButton(android.R.string.ok)
                    .positiveButton { mPresenter.didClickCloseErrorMessage() }
                    .show()
        }
    }

    private fun setupToolbar() {
        toolbar_back_button.setOnClickListener { mPresenter.didClickToolbarBackButton() }
    }
}