package com.wappiter.domain.user.termsandconditions.service

import com.wappiter.domain.base.Service
import com.wappiter.domain.base.globalexception.BaseException

interface TermsAndConditionsService : Service {
    @Throws(BaseException::class)
    fun getAcceptedTermsAndConditions(): Boolean

    @Throws(BaseException::class)
    fun setAcceptedTermsAndConditions(accepted: Boolean)
}