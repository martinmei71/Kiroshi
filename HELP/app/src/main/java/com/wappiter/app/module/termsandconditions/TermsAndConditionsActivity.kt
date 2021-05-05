package com.wappiter.app.module.termsandconditions

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.text.style.UnderlineSpan
import android.view.View
import androidx.core.content.ContextCompat
import com.wappiter.app.R
import com.wappiter.app.base.BaseActivity
import com.wappiter.app.constants.AppConstants
import com.wappiter.app.infrastucture.di.AppComponent
import com.wappiter.app.module.termsandconditions.presenter.TermsAndConditionsPresenter
import com.wappiter.app.module.termsandconditions.presenter.TermsAndConditionsView
import com.wappiter.app.presentation.base.BaseVP
import com.wappiter.app.util.analytics.ScreenNames
import kotlinx.android.synthetic.main.activity_terms_and_conditions.*
import javax.inject.Inject

class TermsAndConditionsActivity : BaseActivity(), TermsAndConditionsView {

    private val TAG = TermsAndConditionsActivity::class.toString()

    @Inject
    lateinit var mPresenter: TermsAndConditionsPresenter

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, TermsAndConditionsActivity::class.java)
        }

        fun newIntentFromDeeplink(context: Context, restaurantId: String): Intent {
            val intent = Intent(context, TermsAndConditionsActivity::class.java)
            intent.putExtra(AppConstants.INTENT_PARAM_RESTAURANT_ID, restaurantId)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_terms_and_conditions)
        mPresenter.attachView(this)
    }

    override fun detachView() {
        mPresenter.detachView()
    }

    override fun getActivityTag(): String {
        return TAG
    }

    override fun getAnalyticsName(): String {
        return ScreenNames.TERMS_AND_CONDITIONS
    }

    override fun getPresenter(): BaseVP.Presenter {
        return mPresenter
    }

    override fun makeInjection(appComponent: AppComponent?) {
        appComponent?.plus(TermsAndConditionsModule(this))?.inject(this)
    }

    override fun showTermsAndConditionsView() {
        terms_and_conditions_view.visibility = View.VISIBLE

        accept_terms_and_conditions_btn.setOnClickListener { mPresenter.didAcceptedTermsAndConditions() }
    }

    override fun setUpView() {
        setSpanTextTerms()
        val restaurantId = intent.getStringExtra(AppConstants.INTENT_PARAM_RESTAURANT_ID)
        if (restaurantId != null && restaurantId.isNotEmpty()) {
            mPresenter.startFlowWithRestaurantId(restaurantId)
        } else {
            mPresenter.startFlow()
        }
    }

    private fun setSpanTextTerms() {
        val completedText = getString(R.string.terms_and_conditions_complete_message)
        val spanString = SpannableString(completedText)
        val textTermsAndPrivacyPolicy = getString(R.string.terms_and_conditions_text)

        spanString.setSpan(UnderlineSpan(),
                completedText.indexOf(textTermsAndPrivacyPolicy),
                completedText.indexOf(textTermsAndPrivacyPolicy) + textTermsAndPrivacyPolicy.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        val policyClickableSpan: ClickableSpan = object : ClickableSpan() {
            override fun onClick(textView: View) {
                mPresenter.didClickTermsAndConditions()
            }
        }

        spanString.setSpan(policyClickableSpan,
                completedText.indexOf(textTermsAndPrivacyPolicy),
                completedText.indexOf(textTermsAndPrivacyPolicy) + textTermsAndPrivacyPolicy.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        spanString.setSpan(ForegroundColorSpan(ContextCompat.getColor(this, R.color.primary_color)),
                completedText.indexOf(textTermsAndPrivacyPolicy),
                completedText.indexOf(textTermsAndPrivacyPolicy) + textTermsAndPrivacyPolicy.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        terms_and_conditions_message_tv.movementMethod = LinkMovementMethod.getInstance()
        terms_and_conditions_message_tv.text = spanString
    }
}