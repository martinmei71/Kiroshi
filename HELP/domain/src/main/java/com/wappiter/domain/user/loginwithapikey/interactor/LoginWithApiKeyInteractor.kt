package com.wappiter.domain.user.loginwithapikey.interactor

import com.wappiter.domain.base.globalexception.BaseException
import com.wappiter.domain.base.interactor.Interactor
import com.wappiter.domain.base.interactor.InteractorResponse
import com.wappiter.domain.user.UserSession
import com.wappiter.domain.user.loginwithapikey.service.LoginWithApiKeyService
import com.wappiter.infrastructure.interactor.InteractorErrorHandler

class LoginWithApiKeyInteractor(private val loginWithApiKeyService: LoginWithApiKeyService, val interactorErrorHandler: InteractorErrorHandler) : Interactor<InteractorResponse<UserSession>> {

    @Throws(BaseException::class)
    override fun call(): InteractorResponse<UserSession> {
        return try {
            InteractorResponse(loginWithApiKeyService.loginWithApiKey())
        } catch (e: BaseException) {
            @Suppress("UNCHECKED_CAST")
            return interactorErrorHandler.handle(e) as InteractorResponse<UserSession>
        }
    }
}