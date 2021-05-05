package com.wappiter.domain.user.logout.interactor

import com.wappiter.domain.base.VoidModel
import com.wappiter.domain.base.globalexception.BaseException
import com.wappiter.domain.base.interactor.Interactor
import com.wappiter.domain.base.interactor.InteractorResponse
import com.wappiter.domain.user.logout.service.LogoutService
import com.wappiter.infrastructure.interactor.InteractorErrorHandler

class LogoutInteractor(val mLogoutService: LogoutService, val mInteractorErrorHandler: InteractorErrorHandler) : Interactor<InteractorResponse<VoidModel>> {
    override fun call(): InteractorResponse<VoidModel> {
        return try {
            mLogoutService.logoutUser()
            InteractorResponse(VoidModel())
        } catch (e: BaseException) {
            @Suppress("UNCHECKED_CAST")
            mInteractorErrorHandler.handle(e) as InteractorResponse<VoidModel>
        }
    }
}