package com.wappiter.domain.user.profile.interactor

import com.wappiter.domain.base.globalexception.BaseException
import com.wappiter.domain.base.interactor.Interactor
import com.wappiter.domain.base.interactor.InteractorResponse
import com.wappiter.domain.user.UserSession
import com.wappiter.domain.user.profile.service.UserProfileService
import com.wappiter.infrastructure.interactor.InteractorErrorHandler

class UserProfileInteractor(private val mUserProfileService: UserProfileService, val mInteractorErrorHandler: InteractorErrorHandler) : Interactor<InteractorResponse<UserSession>> {
    override fun call(): InteractorResponse<UserSession> {
        return try {
            InteractorResponse(mUserProfileService.getUser())
        } catch (e: BaseException) {
            @Suppress("UNCHECKED_CAST")
            mInteractorErrorHandler.handle(e) as InteractorResponse<UserSession>
        }
    }
}