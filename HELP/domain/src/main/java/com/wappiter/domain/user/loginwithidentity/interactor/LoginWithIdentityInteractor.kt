package com.wappiter.domain.user.loginwithidentity.interactor

import com.wappiter.domain.base.globalexception.ApiException
import com.wappiter.domain.base.globalexception.BaseException
import com.wappiter.domain.base.interactor.Interactor
import com.wappiter.domain.base.interactor.InteractorResponse
import com.wappiter.domain.user.UserSession
import com.wappiter.domain.user.loginwithidentity.service.LoginWithIdentityService
import com.wappiter.domain.user.registration.service.RegistrationService
import com.wappiter.infrastructure.exceptions.ErrorCodes
import com.wappiter.infrastructure.interactor.InteractorErrorHandler

class LoginWithIdentityInteractor(private val loginWithIdentityService: LoginWithIdentityService,
                                  private val mRegistrationService: RegistrationService,
                                  val interactorErrorHandler: InteractorErrorHandler) : Interactor<InteractorResponse<UserSession>> {

    lateinit var mValues: LoginWithIdentityInteractorValues

    fun setInteractorValues(values: LoginWithIdentityInteractorValues) {
        mValues = values
    }

    @Throws(BaseException::class)
    override fun call(): InteractorResponse<UserSession> {
        return try {
            InteractorResponse(
                    loginWithIdentityService.loginWithIdentity(
                            mValues.firstName,
                            mValues.lastName,
                            mValues.email,
                            mValues.photoUrl,
                            mValues.userId,
                            mValues.idToken,
                            mValues.provider))
        } catch (e: BaseException) {
            if (e is ApiException) {
                val apiException = e
                if (ErrorCodes.API_IDENTITY_NOT_FOUND == apiException.httpCode.toString()) {
                    return this.registerUser()
                }
            }

            @Suppress("UNCHECKED_CAST")
            return interactorErrorHandler.handle(e) as InteractorResponse<UserSession>
        }
    }

    fun registerUser(): InteractorResponse<UserSession> {
        return try {
            InteractorResponse(mRegistrationService.register(mValues.firstName,
                    mValues.lastName,
                    mValues.email,
                    mValues.photoUrl,
                    mValues.userId,
                    mValues.idToken,
                    mValues.provider,
                    null)) //La contraseña será null cuando este servicio se envie tras un loginWithIdentity en el cual no exista el usuario en BD

        } catch (e: BaseException) {
            @Suppress("UNCHECKED_CAST")
            interactorErrorHandler.handle(e) as InteractorResponse<UserSession>
        }
    }
}