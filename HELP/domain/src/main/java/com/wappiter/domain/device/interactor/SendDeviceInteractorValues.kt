package com.wappiter.domain.device.interactor

import com.wappiter.domain.base.interactor.InteractorValues


class SendDeviceInteractorValues(val token: String,
                                 val os: String,
                                 val version: String,
                                 val deviceUuid: String) : InteractorValues()
