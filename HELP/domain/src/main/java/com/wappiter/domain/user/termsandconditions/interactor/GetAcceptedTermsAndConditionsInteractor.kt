package com.wappiter.domain.user.termsandconditions.interactor

import com.wappiter.domain.base.globalexception.BaseException
import com.wappiter.domain.base.interactor.Interactor
import com.wappiter.domain.base.interactor.InteractorResponse
import com.wappiter.domain.user.termsandconditions.service.TermsAndConditionsService
import com.wappiter.infrastructure.interactor.InteractorErrorHandler

class GetAcceptedTermsAndConditionsInteractor(private val mTermsAndConditionsService: TermsAndConditionsService, val mInteractorErrorHandler: InteractorErrorHandler) : Interactor<InteractorResponse<Boolean>> {
    override fun call(): InteractorResponse<Boolean> {
        return try {
            InteractorResponse(mTermsAndConditionsService.getAcceptedTermsAndConditions())
        } catch (e: BaseException) {
            e.printStackTrace()
            @Suppress("UNCHECKED_CAST")
            mInteractorErrorHandler.handle(e) as InteractorResponse<Boolean>
        }
    }
}