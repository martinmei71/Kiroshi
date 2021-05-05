package com.wappiter.domain.user.profile.interactor

import com.wappiter.domain.base.globalexception.BaseException
import com.wappiter.domain.base.interactor.Interactor
import com.wappiter.domain.base.interactor.InteractorResponse
import com.wappiter.domain.user.UserSession
import com.wappiter.domain.user.session.service.UserSessionService
import com.wappiter.infrastructure.interactor.InteractorErrorHandler

class GetUserLoggedInteractor(private val mUserSessionService: UserSessionService, val mInteractorErrorHandler: InteractorErrorHandler) : Interactor<InteractorResponse<UserSession>> {
    override fun call(): InteractorResponse<UserSession> {
        return try {
            InteractorResponse(mUserSessionService.getLoggedUser())
        } catch (e: BaseException) {
            e.printStackTrace()
            @Suppress("UNCHECKED_CAST")
            mInteractorErrorHandler.handle(e) as InteractorResponse<UserSession>
        }
    }
}