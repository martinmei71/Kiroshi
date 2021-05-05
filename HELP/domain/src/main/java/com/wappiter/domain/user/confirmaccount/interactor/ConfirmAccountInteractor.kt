package com.wappiter.domain.user.confirmaccount.interactor

import com.wappiter.domain.base.globalexception.BaseException
import com.wappiter.domain.base.interactor.Interactor
import com.wappiter.domain.base.interactor.InteractorResponse
import com.wappiter.domain.user.confirmaccount.service.ConfirmAccountService
import com.wappiter.infrastructure.interactor.InteractorErrorHandler


class ConfirmAccountInteractor(private val confirmAccountService: ConfirmAccountService, val interactorErrorHandler: InteractorErrorHandler) : Interactor<InteractorResponse<Void>> {

    lateinit var mValues: ConfirmAccountInteractorValues
    fun setInteractorValues(values: ConfirmAccountInteractorValues) {
        mValues = values
    }

    override fun call(): InteractorResponse<Void> {
        try {
            confirmAccountService.confirm(mValues.code)
            return InteractorResponse()
        } catch (e: BaseException) {
            @Suppress("UNCHECKED_CAST")
            return interactorErrorHandler.handle(e) as InteractorResponse<Void>
        }
    }
}