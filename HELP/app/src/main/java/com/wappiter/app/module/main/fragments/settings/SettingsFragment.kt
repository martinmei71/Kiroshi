package com.wappiter.app.module.main.fragments.settings

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.wappiter.app.R
import com.wappiter.app.base.BaseFragment
import com.wappiter.app.infrastucture.di.AppComponent
import com.wappiter.app.module.main.fragments.settings.presenter.SettingsFragmentPresenter
import com.wappiter.app.module.main.fragments.settings.presenter.SettingsFragmentView
import com.wappiter.app.presentation.base.BaseVP
import com.wappiter.app.util.SystemUtils
import com.wappiter.app.util.analytics.ScreenNames
import com.wappiter.app.util.bus.OnInitSessionEvent
import kotlinx.android.synthetic.main.fragment_settings.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import javax.inject.Inject

class SettingsFragment : BaseFragment(), SettingsFragmentView {

    val TAG: String = SettingsFragment::class.toString()

    @Inject
    lateinit var mPresenter: SettingsFragmentPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
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
        appComponent?.plus(SettingsFragmentModule(requireActivity()))?.inject(this)
    }

    override fun showLoginButton() {
        settings_login_button.visibility = View.VISIBLE
    }

    override fun showMyProfileButton() {
        settings_my_profile_button.visibility = View.VISIBLE
    }

    override fun showLogoutButton() {
        settings_logout_button.visibility = View.VISIBLE
        separator_settings_logout_button.visibility = View.VISIBLE
    }

    override fun hideLoginButton() {
        settings_login_button.visibility = View.GONE
    }

    override fun hideMyProfileButton() {
        settings_my_profile_button.visibility = View.GONE
    }

    override fun hideLogoutButton() {
        settings_logout_button.visibility = View.GONE
        separator_settings_logout_button.visibility = View.GONE
    }

    override fun setUpView() {
        setupLoginButton()
        setupMyProfileButton()
        setupTermsAndConditionsButton()
        setupWebButton()
        setupLogoutButton()
        setupRestaurantManagerButton()
        setupRateAppButton()
        mPresenter.startFlow()
    }

    override fun goToStore() {
        val appPackageName = SystemUtils
                .packageName(requireContext()) // getPackageName() from Context or Activity
        try {
            startActivity(Intent(Intent.ACTION_VIEW,
                    Uri.parse("market://details?id=$appPackageName")))
        } catch (anfe: ActivityNotFoundException) {
            startActivity(Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://play.google.com/store/apps/details?id=$appPackageName")))
        }
    }

    @Subscribe
    fun onInitSessionEvent(event: OnInitSessionEvent) {
        mPresenter.didOnInitSession()
    }

    private fun setupLoginButton() {
        (settings_login_button as TextView).text = getString(R.string.settings_login_button_title)
        settings_login_button.setOnClickListener { mPresenter.didClickLoginButton() }
    }

    private fun setupMyProfileButton() {
        (settings_my_profile_button as TextView).text = getString(R.string.settings_my_profile_button)
        settings_my_profile_button.setOnClickListener { mPresenter.didClickMyProfileButton() }
    }

    private fun setupTermsAndConditionsButton() {
        (settings_terms_and_conditions_button as TextView).text = getString(R.string.settings_terms_and_conditions_button)
        settings_terms_and_conditions_button.setOnClickListener { mPresenter.didClickTermsAndConditionsButton() }
    }

    private fun setupWebButton() {
        (settings_web_button as TextView).text = getString(R.string.settings_web_button)
        settings_web_button.setOnClickListener { mPresenter.didClickWebButton() }
    }

    private fun setupLogoutButton() {
        (settings_logout_button as TextView).text = getString(R.string.settings_logout_button)
        settings_logout_button.setOnClickListener { mPresenter.didClickLogoutButton() }
    }

    private fun setupRateAppButton() {
        (settings_rate_app_button as TextView).text = getString(R.string.settings_rate_app_button)
        settings_rate_app_button.setOnClickListener { mPresenter.didClickRateAppButton() }
    }

    private fun setupRestaurantManagerButton() {
        (settings_restaurant_manager_button as TextView).text = getString(R.string.settings_restaurant_manager_button)
        settings_restaurant_manager_button.setOnClickListener { mPresenter.didClickRestaurantManagerButton() }
    }
}