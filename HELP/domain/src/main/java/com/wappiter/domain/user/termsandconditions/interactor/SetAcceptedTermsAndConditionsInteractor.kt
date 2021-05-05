package com.wappiter.domain.user.termsandconditions.interactor

import com.wappiter.domain.base.VoidModel
import com.wappiter.domain.base.globalexception.BaseException
import com.wappiter.domain.base.interactor.Interactor
import com.wappiter.domain.base.interactor.InteractorResponse
import com.wappiter.domain.user.termsandconditions.service.TermsAndConditionsService
import com.wappiter.infrastructure.interactor.InteractorErrorHandler

class SetAcceptedTermsAndConditionsInteractor(private val mTermsAndConditionsService: TermsAndConditionsService, val mInteractorErrorHandler: InteractorErrorHandler) : Interactor<InteractorResponse<VoidModel>> {

    lateinit var mValues: SetAcceptedTermsAndConditionsInteractorValues

    fun setInteractorValues(values: SetAcceptedTermsAndConditionsInteractorValues) {
        mValues = values
    }

    override fun call(): InteractorResponse<VoidModel> {
        return try {
            mTermsAndConditionsService.setAcceptedTermsAndConditions(mValues.accepted)
            InteractorResponse(VoidModel())
        } catch (e: BaseException) {
            e.printStackTrace()
            @Suppress("UNCHECKED_CAST")
            mInteractorErrorHandler.handle(e) as InteractorResponse<VoidModel>
        }
    }
}