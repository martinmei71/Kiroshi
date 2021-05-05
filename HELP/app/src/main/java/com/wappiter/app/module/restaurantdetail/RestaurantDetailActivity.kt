package com.wappiter.app.module.restaurantdetail

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import com.afollestad.materialdialogs.MaterialDialog
import com.wappiter.app.BuildConfig
import com.wappiter.app.R
import com.wappiter.app.base.BaseActivity
import com.wappiter.app.constants.AppConstants
import com.wappiter.app.infrastucture.di.AppComponent
import com.wappiter.app.module.restaurantdetail.presenter.RestaurantDetailPresenter
import com.wappiter.app.module.restaurantdetail.presenter.RestaurantDetailView
import com.wappiter.app.presentation.base.BaseVP
import com.wappiter.app.util.analytics.ScreenNames
import com.wappiter.app.util.bus.OnInitSessionEvent
import com.wappiter.app.util.webview.WebviewUtils
import kotlinx.android.synthetic.main.activity_restaurant_detail.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import javax.inject.Inject

class RestaurantDetailActivity : BaseActivity(), RestaurantDetailView {

    private val TAG = RestaurantDetailActivity::class.toString()

    @Inject
    lateinit var mPresenter: RestaurantDetailPresenter

    companion object {
        fun newIntent(context: Context, restaurantId: String): Intent {
            val intent = Intent(context, RestaurantDetailActivity::class.java)
            intent.putExtra(AppConstants.INTENT_PARAM_RESTAURANT_ID, restaurantId)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant_detail)
        EventBus.getDefault().register(this)
        mPresenter.attachView(this)
    }

    override fun detachView() {
        mPresenter.detachView()
    }

    override fun onDestroy() {
        EventBus.getDefault().unregister(this)
        super.onDestroy()
    }

    override fun getActivityTag(): String {
        return TAG
    }

    override fun getAnalyticsName(): String {
        return ScreenNames.RESTAURANT_DETAIL
    }

    override fun getPresenter(): BaseVP.Presenter {
        return mPresenter
    }

    override fun makeInjection(appComponent: AppComponent?) {
        appComponent?.plus(RestaurantDetailModule(this))?.inject(this)
    }

    override fun setUpView() {
        setupToolbar()
        setupMyOrdersButton()
        val restaurantId = intent.getStringExtra(AppConstants.INTENT_PARAM_RESTAURANT_ID)
        mPresenter.startFlow(restaurantId)
    }

    override fun initWebview(url: String, userApiKey: String?) {
        val webViewClient: WebViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView,
                                        url: String) {
                if (BuildConfig.DEBUG) {
                    Log.d("RestaurantDetailWebview", "onPageFinished -> $url")

                }
                super.onPageFinished(view, url)
            }

            override fun shouldOverrideUrlLoading(view: WebView,
                                                  url: String): Boolean {
                if (BuildConfig.DEBUG) {
                    Log.d("RestaurantDetailWebview", "shouldOverrideUrlLoading -> $url")
                }

                if (url.contains(AppConstants.LOGIN_URL)) {
                    mPresenter.interceptOpenLogin()
                    return true
                }
                view.loadUrl(url, WebviewUtils().getWebviewHeaders(userApiKey))
                return false
            }
        }
        restaurant_detail_webview.webViewClient = webViewClient
        restaurant_detail_webview.settings.javaScriptEnabled = true
        restaurant_detail_webview.loadUrl(url, WebviewUtils().getWebviewHeaders(userApiKey))
    }

    override fun showErrorNotUserLoggedToGoMyOrders() {
        MaterialDialog(this).show {
            title(R.string.app_name)
            message(R.string.restaurant_detail_my_orders_not_logged_user)
            positiveButton(R.string.accept) { mPresenter.didClickGoToLogin() }
            negativeButton(R.string.cancel)
            cancelOnTouchOutside(false)
        }
    }

    @Subscribe
    fun onInitSessionEvent(event: OnInitSessionEvent) {
        mPresenter.didOnInitSession()
    }

    private fun setupToolbar() {
        restaurant_detail_toolbar_back_button.setOnClickListener { mPresenter.didClickToolbarBackButton() }
    }

    private fun setupMyOrdersButton() {
        restaurant_detail_toolbar_my_orders_button.setOnClickListener { mPresenter.didClickMyOrdersButton() }
    }
}