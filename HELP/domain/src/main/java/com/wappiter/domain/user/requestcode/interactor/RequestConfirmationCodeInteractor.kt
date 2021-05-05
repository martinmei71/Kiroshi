package com.wappiter.domain.user.requestcode.interactor

import com.wappiter.domain.base.VoidModel
import com.wappiter.domain.base.globalexception.BaseException
import com.wappiter.domain.base.interactor.Interactor
import com.wappiter.domain.base.interactor.InteractorResponse
import com.wappiter.domain.user.requestcode.service.RequestConfirmationCodeService
import com.wappiter.infrastructure.interactor.InteractorErrorHandler

class RequestConfirmationCodeInteractor(private val mRequestConfirmationCodeService: RequestConfirmationCodeService,
                                        val mInteractorErrorHandler: InteractorErrorHandler) : Interactor<InteractorResponse<VoidModel>> {

    override fun call(): InteractorResponse<VoidModel> {
        return try {
            mRequestConfirmationCodeService.requestConfirmationCode()
            InteractorResponse(VoidModel())
        } catch (e: BaseException) {
            e.printStackTrace()
            @Suppress("UNCHECKED_CAST")
            mInteractorErrorHandler.handle(e) as InteractorResponse<VoidModel>
        }
    }
}