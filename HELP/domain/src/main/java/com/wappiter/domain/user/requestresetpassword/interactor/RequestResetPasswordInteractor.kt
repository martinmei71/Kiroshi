package com.wappiter.domain.user.requestresetpassword.interactor

import com.wappiter.domain.base.VoidModel
import com.wappiter.domain.base.globalexception.BaseException
import com.wappiter.domain.base.interactor.Interactor
import com.wappiter.domain.base.interactor.InteractorResponse
import com.wappiter.domain.user.requestresetpassword.service.RequestResetPasswordService
import com.wappiter.infrastructure.interactor.InteractorErrorHandler

class RequestResetPasswordInteractor(private var mRequestResetPasswordService: RequestResetPasswordService,
                                     var mInteractorErrorHandler: InteractorErrorHandler) : Interactor<InteractorResponse<VoidModel>> {

    lateinit var mValues: RequestResetPasswordInteractorValues

    fun setInteractorValues(values: RequestResetPasswordInteractorValues) {
        mValues = values
    }

    @Throws(BaseException::class)
    override fun call(): InteractorResponse<VoidModel> {
        return try {
            mRequestResetPasswordService.requestResetPassword(mValues.email)
            InteractorResponse(VoidModel())
        } catch (e: BaseException) {
            e.printStackTrace()
            @Suppress("UNCHECKED_CAST")
            mInteractorErrorHandler.handle(e) as InteractorResponse<VoidModel>
        }
    }
}