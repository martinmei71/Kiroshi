package com.wappiter.data.user.termsandconditions

import com.wappiter.data.apppreferences.PreferencesHelper
import com.wappiter.domain.user.termsandconditions.data.TermsAndConditionsDatasource

open class TermsAndConditionsDatasourceImp(private val preferencesHelper: PreferencesHelper) : TermsAndConditionsDatasource {

    private val ACCEPTED_TERMS_AND_CONDITIONS_SESSION_KEY: String = "accepted_terms_and_conditions"

    override fun getAcceptedTermsAndConditions(): Boolean {
        return preferencesHelper.getPref(ACCEPTED_TERMS_AND_CONDITIONS_SESSION_KEY, false)
    }

    override fun setAcceptedTermsAndConditions(accepted: Boolean) {
        preferencesHelper.savePref(ACCEPTED_TERMS_AND_CONDITIONS_SESSION_KEY, accepted)
    }
}