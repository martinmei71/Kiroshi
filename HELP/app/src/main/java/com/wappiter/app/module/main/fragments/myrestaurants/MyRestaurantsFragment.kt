package com.wappiter.app.module.main.fragments.myrestaurants

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
import android.webkit.WebViewClient
import com.afollestad.materialdialogs.MaterialDialog
import com.wappiter.app.BuildConfig
import com.wappiter.app.R
import com.wappiter.app.base.BaseFragment
import com.wappiter.app.data.constants.environment.EnvironmentConstants
import com.wappiter.app.infrastucture.di.AppComponent
import com.wappiter.app.module.main.MainActivity
import com.wappiter.app.module.main.fragments.myrestaurants.presenter.MyRestaurantFragmentPresenter
import com.wappiter.app.module.main.fragments.myrestaurants.presenter.MyRestaurantsFragmentView
import com.wappiter.app.presentation.base.BaseVP
import com.wappiter.app.util.analytics.ScreenNames
import com.wappiter.app.util.bus.OnAddFavouriteRestaurantEvent
import com.wappiter.app.util.bus.OnCloseSessionEvent
import com.wappiter.app.util.bus.OnInitSessionEvent
import com.wappiter.app.util.bus.OnSelectMyRestaurantTabEvent
import com.wappiter.app.util.webview.WebviewUtils
import kotlinx.android.synthetic.main.fragment_my_restaurants.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import javax.inject.Inject

class MyRestaurantsFragment : BaseFragment(), MyRestaurantsFragmentView {

    val TAG: String = MyRestaurantsFragment::class.toString()

    @Inject
    lateinit var mPresenter: MyRestaurantFragmentPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_my_restaurants, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)
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

    override fun getFragmentTag(): String {
        return TAG
    }

    override fun getAnalyticsName(): String {
        return ScreenNames.MY_RESTAURANTS
    }

    override fun getPresenter(): BaseVP.Presenter {
        return mPresenter
    }

    override fun makeInjection(appComponent: AppComponent?) {
        appComponent?.plus(MyRestaurantsFragmentModule(requireActivity()))?.inject(this)
    }

    override fun setUpView() {
        my_restaurant_empty_list_button.setOnClickListener { mPresenter.didClickEmptyListButton() }
        mPresenter.startFlow()
    }

    override fun showLoginInfoDialog() {
        val parentActivity: Activity? = activity
        if (parentActivity != null && !parentActivity.isDestroyed) {
            MaterialDialog(parentActivity).show {
                title(R.string.my_restaurants_login_dialog_title)
                message(R.string.my_restaurants_login_dialog_message)
                positiveButton(R.string.accept) { mPresenter.didClickEnterOnWappiter() }
                negativeButton(R.string.cancel)
                cancelOnTouchOutside(false)
            }
        }
    }

    override fun showEmptyListView() {
        my_restaurant_empty_list_view.visibility = View.VISIBLE
    }

    override fun hideEmptyListView() {
        my_restaurant_empty_list_view.visibility = View.GONE
    }

    override fun showMyRestaurantsWebview() {
        my_restaurants_webview.visibility = View.VISIBLE
    }

    override fun hideMyRestaurantsWebview() {
        my_restaurants_webview.visibility = View.GONE
    }

    override fun setupMyRestaurantsWebview(restaurantsUrl: String, userApiKey: String) {
        val webViewClient: WebViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView,
                                        url: String) {
                if (BuildConfig.DEBUG) {
                    Log.d("MyRestaurantsFragment", "onPageFinished -> $url")

                }
                super.onPageFinished(view, url)
            }

            override fun shouldInterceptRequest(view: WebView?, request: WebResourceRequest?): WebResourceResponse? {
                if (BuildConfig.DEBUG) {
                    Log.d("shouldInterceptRequest", request?.requestHeaders.toString())
                }
                return super.shouldInterceptRequest(view, request)
            }

            override fun shouldOverrideUrlLoading(view: WebView,
                                                  url: String): Boolean {
                if (BuildConfig.DEBUG) {
                    Log.d("MyRestaurantsFragment", "shouldOverrideUrlLoading -> $url")

                }

                val navigateToRestaurantDetail = url.contains(EnvironmentConstants.RESTAURANTS_URL)
                return if (navigateToRestaurantDetail) {
                    mPresenter.didClickRestaurant(url)
                    true
                } else {
                    view.loadUrl(url, WebviewUtils().getWebviewHeaders(userApiKey))
                    false
                }
            }
        }

        (my_restaurants_webview as WebView).webViewClient = webViewClient
        (my_restaurants_webview as WebView).settings.javaScriptEnabled = true
        (my_restaurants_webview as WebView).loadUrl(restaurantsUrl, WebviewUtils().getWebviewHeaders(userApiKey))
    }

    override fun selectScannerTab() {
        (activity as MainActivity).selectScannerTab()
    }

    @Subscribe
    fun onSelectRequestTabEvent(event: OnSelectMyRestaurantTabEvent) {
        mPresenter.didOnSelectMyRestaurantTab()
    }

    @Subscribe
    fun onInitSessionEvent(event: OnInitSessionEvent) {
        mPresenter.didOnInitSession()
    }

    @Subscribe
    fun onCloseSessionEvent(event: OnCloseSessionEvent) {
        mPresenter.didOnCloseSession()
    }

    @Subscribe
    fun onAddFavouriteRestaurantEvent(event: OnAddFavouriteRestaurantEvent) {
        mPresenter.didOnAddFavouriteRestaurantEvent()
    }
}