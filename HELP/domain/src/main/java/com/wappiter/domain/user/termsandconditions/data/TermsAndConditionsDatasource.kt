package com.wappiter.domain.user.termsandconditions.data

interface TermsAndConditionsDatasource {

    fun getAcceptedTermsAndConditions(): Boolean

    fun setAcceptedTermsAndConditions(accepted: Boolean)
}