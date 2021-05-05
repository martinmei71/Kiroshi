package com.wappiter.domain.user.login.interactor

import com.wappiter.domain.base.globalexception.BaseException
import com.wappiter.domain.base.interactor.Interactor
import com.wappiter.domain.base.interactor.InteractorResponse
import com.wappiter.domain.user.UserSession
import com.wappiter.domain.user.login.service.LoginService
import com.wappiter.infrastructure.interactor.InteractorErrorHandler

class LoginInteractor(private val loginService: LoginService, val interactorErrorHandler: InteractorErrorHandler) : Interactor<InteractorResponse<UserSession>> {

    lateinit var mValues: LoginInteractorValues

    fun setInteractorValues(values: LoginInteractorValues) {
        mValues = values
    }

    @Throws(BaseException::class)
    override fun call(): InteractorResponse<UserSession> {
        return try {
            InteractorResponse(loginService.login(mValues.email, mValues.password))
        } catch (e: BaseException) {
            @Suppress("UNCHECKED_CAST")
            return interactorErrorHandler.handle(e) as InteractorResponse<UserSession>
        }
    }
}