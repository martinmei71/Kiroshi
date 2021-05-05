package com.wappiter.domain.device.service

import com.wappiter.domain.base.globalexception.ApiException
import com.wappiter.domain.base.globalexception.MapperException
import com.wappiter.domain.base.globalexception.NetworkException
import com.wappiter.domain.device.data.SendDeviceDatasource
import com.wappiter.domain.device.data.SendDeviceDatasourceParams
import com.wappiter.infrastructure.datasource.AppPreferencesDatasource

class SendDeviceServiceImp(private val sendDeviceDatasource: SendDeviceDatasource, private val appPreferencesDatasource: AppPreferencesDatasource) : SendDeviceService {

    private val SENT_TOKEN: String = "sent_token"

    @Throws(NetworkException::class, ApiException::class, MapperException::class)
    override fun sendDevice(token: String,
                            os: String,
                            version: String,
                            deviceUuid: String) {

        val sentToken = appPreferencesDatasource.getBoolean(SENT_TOKEN)

        if (!sentToken) {
            sendDeviceDatasource
                    .sendDevice(SendDeviceDatasourceParams(token, os, version, deviceUuid))
            appPreferencesDatasource.saveBoolean(SENT_TOKEN, true)
        }
    }
}
