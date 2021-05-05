package com.wappiter.domain.user.termsandconditions.service

import com.wappiter.domain.base.globalexception.BaseException
import com.wappiter.domain.user.termsandconditions.data.TermsAndConditionsDatasource

class TermsAndConditionsServiceImp(private val mTermsAndConditionsDatasource: TermsAndConditionsDatasource) : TermsAndConditionsService {

    @Throws(BaseException::class)
    override fun getAcceptedTermsAndConditions(): Boolean {
        return mTermsAndConditionsDatasource.getAcceptedTermsAndConditions()
    }

    @Throws(BaseException::class)
    override fun setAcceptedTermsAndConditions(accepted: Boolean) {
        mTermsAndConditionsDatasource.setAcceptedTermsAndConditions(accepted)
    }
}