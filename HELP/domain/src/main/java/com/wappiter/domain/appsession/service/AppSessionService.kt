package com.wappiter.domain.appsession.service

import com.wappiter.domain.appsession.exception.ExpiredAppVersionException
import com.wappiter.domain.base.globalexception.ApiException
import com.wappiter.domain.base.globalexception.MapperException
import com.wappiter.domain.base.globalexception.NetworkException

interface AppSessionService {
    @Throws(NetworkException::class, ApiException::class, MapperException::class, ExpiredAppVersionException::class)
    fun createAppSession(appVersionCode: String,
                         appVersionName: String,
                         networkType: String,
                         deviceUniqueIdentifier: String,
                         mobileOperatingSystemName: String,
                         mobileOperatingSystemVersionName: String,
                         deviceManufacturerName: String,
                         deviceModelName: String)

    fun cleanAppSession()
}