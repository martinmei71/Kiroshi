package com.wappiter.domain.user.resetpassword.interactor

import com.wappiter.domain.base.VoidModel
import com.wappiter.domain.base.globalexception.ApiException
import com.wappiter.domain.base.globalexception.BaseException
import com.wappiter.domain.base.interactor.Interactor
import com.wappiter.domain.base.interactor.InteractorResponse
import com.wappiter.domain.user.resetpassword.service.ResetPasswordService
import com.wappiter.domain.user.session.service.UserSessionService
import com.wappiter.infrastructure.interactor.InteractorErrorHandler

class ResetPasswordInteractor(private var mResetPasswordService: ResetPasswordService,
                              private var mUserSessionService: UserSessionService,
                              var mInteractorErrorHandler: InteractorErrorHandler) : Interactor<InteractorResponse<VoidModel>> {

    lateinit var mValues: ResetPasswordInteractorValues

    fun setInteractorValues(values: ResetPasswordInteractorValues) {
        mValues = values
    }

    @Throws(ApiException::class, BaseException::class)
    override fun call(): InteractorResponse<VoidModel> {
        return try {
            val userSession = mUserSessionService.getLoggedUser()
            mResetPasswordService.resetPassword(userSession.user.email, mValues.code, mValues.password)
            InteractorResponse(VoidModel())
        } catch (e: BaseException) {
            e.printStackTrace()
            @Suppress("UNCHECKED_CAST")
            mInteractorErrorHandler.handle(e) as InteractorResponse<VoidModel>
        }
    }
}