package com.wappiter.domain.device.service

import com.wappiter.domain.base.globalexception.ApiException
import com.wappiter.domain.base.globalexception.MapperException
import com.wappiter.domain.base.globalexception.NetworkException

interface SendDeviceService {

    @Throws(NetworkException::class, ApiException::class, MapperException::class)
    fun sendDevice(token: String,
                   os: String,
                   version: String,
                   deviceUuid: String)
}
