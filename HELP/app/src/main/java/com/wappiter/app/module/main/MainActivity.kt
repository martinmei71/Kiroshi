package com.wappiter.app.module.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayoutMediator
import com.wappiter.app.R
import com.wappiter.app.base.BaseActivity
import com.wappiter.app.constants.AppConstants
import com.wappiter.app.infrastucture.di.AppComponent
import com.wappiter.app.module.main.adapter.MainViewPagerAdapter
import com.wappiter.app.module.main.presenter.MainPresenter
import com.wappiter.app.module.splash.presenter.MainView
import com.wappiter.app.presentation.base.BaseVP
import com.wappiter.app.util.analytics.ScreenNames
import com.wappiter.app.util.bus.OnSelectMyRestaurantTabEvent
import org.greenrobot.eventbus.EventBus
import javax.inject.Inject


class MainActivity : BaseActivity(), MainView {

    private var TAG: String = MainActivity::class.toString()

    protected lateinit var tabLayout: TabLayout
    protected lateinit var viewPager: ViewPager2
    protected lateinit var mAdapter: MainViewPagerAdapter

    @Inject
    lateinit var mPresenter: MainPresenter

    companion object {
        fun newIntent(context: Context): Intent {
            val intent = Intent(context, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            return intent
        }

        fun newIntentFromDeeplink(context: Context, restaurantId: String): Intent {
            val intent = Intent(context, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.putExtra(AppConstants.INTENT_PARAM_RESTAURANT_ID, restaurantId)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mPresenter.attachView(this)
    }

    override fun detachView() {
        mPresenter.detachView()
    }

    override fun getActivityTag(): String {
        return TAG
    }

    override fun getAnalyticsName(): String {
        return ScreenNames.MAIN
    }

    override fun getPresenter(): BaseVP.Presenter {
        return mPresenter
    }

    override fun makeInjection(appComponent: AppComponent?) {
        appComponent?.plus(MainModule(this))?.inject(this)
    }

    override fun setUpView() {
        val restaurantId = intent.getStringExtra(AppConstants.INTENT_PARAM_RESTAURANT_ID)
        if (restaurantId != null && restaurantId.isNotEmpty()) {
            mPresenter.startFlowWithRestaurantId(restaurantId)
        } else {
            mPresenter.startFlow()
        }
    }

    override fun configureViewPager() {
        tabLayout = findViewById(R.id.main_tab_layout)
        viewPager = findViewById(R.id.main_view_pager)

        mAdapter = MainViewPagerAdapter(supportFragmentManager, this)
        viewPager.offscreenPageLimit = 2
        viewPager.adapter = mAdapter
        tabLayout.addTab(tabLayout.newTab())
        tabLayout.addTab(tabLayout.newTab())
        tabLayout.addTab(tabLayout.newTab())

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = mAdapter.getTabTitle(position)
            tab.icon = mAdapter.getTabIconOff(position)
        }.attach()

        tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                tab.icon = mAdapter.getTabIconOn(tab.position)
                if (mAdapter.TAB_MY_RESTAURANTS_INDEX == tab.position) {
                    EventBus.getDefault().post(OnSelectMyRestaurantTabEvent())
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                tab.icon = mAdapter.getTabIconOff(tab.position)
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
                tab.icon = mAdapter.getTabIconOn(tab.position)
            }
        })

        tabLayout.selectTab(tabLayout.getTabAt(mAdapter.TAB_SCANNER_INDEX))
    }

    override fun selectScannerTab() {
        tabLayout.selectTab(tabLayout.getTabAt(mAdapter.TAB_SCANNER_INDEX))
    }
}