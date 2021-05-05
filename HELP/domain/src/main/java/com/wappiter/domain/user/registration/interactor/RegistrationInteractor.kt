package com.wappiter.domain.user.registration.interactor

import com.wappiter.domain.base.globalexception.BaseException
import com.wappiter.domain.base.interactor.Interactor
import com.wappiter.domain.base.interactor.InteractorResponse
import com.wappiter.domain.user.UserSession
import com.wappiter.domain.user.registration.service.RegistrationService
import com.wappiter.infrastructure.interactor.InteractorErrorHandler

class RegistrationInteractor(private val mRegistrationService: RegistrationService,
                             val mInteractorErrorHandler: InteractorErrorHandler) : Interactor<InteractorResponse<UserSession>> {

    lateinit var mValues: RegistrationInteractorValues

    fun setInteractorValues(values: RegistrationInteractorValues) {
        mValues = values
    }

    override fun call(): InteractorResponse<UserSession> {
        return try {
            InteractorResponse(mRegistrationService.register(mValues.firstName,
                    mValues.lastName,
                    mValues.email,
                    mValues.photoUrl,
                    null,
                    null,
                    mValues.provider,
                    mValues.password))
        } catch (e: BaseException) {
            @Suppress("UNCHECKED_CAST")
            mInteractorErrorHandler.handle(e) as InteractorResponse<UserSession>
        }
    }
}