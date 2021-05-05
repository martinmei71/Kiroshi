package com.wappiter.domain.device.interactor

import com.wappiter.domain.base.VoidModel
import com.wappiter.domain.base.globalexception.BaseException
import com.wappiter.domain.base.interactor.Interactor
import com.wappiter.domain.base.interactor.InteractorResponse
import com.wappiter.domain.device.service.SendDeviceService
import com.wappiter.infrastructure.interactor.InteractorErrorHandler


class SendDeviceInteractor(private val sendDeviceService: SendDeviceService,
                           private val interactorErrorHandler: InteractorErrorHandler) : Interactor<InteractorResponse<VoidModel>> {

    private lateinit var values: SendDeviceInteractorValues

    fun setInteractorValues(values: SendDeviceInteractorValues) {

        this.values = values
    }

    @Suppress("UNCHECKED_CAST")
    override fun call(): InteractorResponse<VoidModel> {
        return try {
            sendDeviceService.sendDevice(values.token, values.os, values.version, values.deviceUuid)
            InteractorResponse(VoidModel())

        } catch (e: BaseException) {
            interactorErrorHandler.handle(e) as InteractorResponse<VoidModel>
        }
    }
}
