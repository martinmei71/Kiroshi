package com.wappiter.app.module.termsandconditions.presenter

import com.wappiter.app.presentation.base.BaseVP

interface TermsAndConditionsPresenter : BaseVP.Presenter {

    fun startFlow()

    fun startFlowWithRestaurantId(restaurantId: String)

    fun didClickTermsAndConditions()

    fun didAcceptedTermsAndConditions()
}