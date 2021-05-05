package com.wappiter.domain.device.data

import com.wappiter.domain.base.globalexception.ApiException
import com.wappiter.domain.base.globalexception.MapperException
import com.wappiter.domain.base.globalexception.NetworkException

interface SendDeviceDatasource {

    @Throws(NetworkException::class, ApiException::class, MapperException::class)
    fun sendDevice(params: SendDeviceDatasourceParams)
}
