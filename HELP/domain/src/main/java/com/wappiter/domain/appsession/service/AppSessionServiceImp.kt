package com.wappiter.domain.appsession.service

import com.wappiter.domain.appsession.data.AppSessionDatasource
import com.wappiter.domain.appsession.data.AppSessionDatasourceParams
import com.wappiter.domain.appsession.exception.ExpiredAppVersionException
import com.wappiter.domain.base.globalexception.ApiException
import com.wappiter.domain.base.globalexception.MapperException
import com.wappiter.domain.base.globalexception.NetworkException
import com.wappiter.infrastructure.datasource.AppPreferencesDatasource
import com.wappiter.infrastructure.exceptions.ErrorCodes

class AppSessionServiceImp(private val mAppSessionDatasource: AppSessionDatasource,
                           private val mAppPreferencesDatasource: AppPreferencesDatasource) : AppSessionService {
    @Throws(NetworkException::class, ApiException::class, MapperException::class, ExpiredAppVersionException::class)
    override fun createAppSession(appVersionCode: String,
                                  appVersionName: String,
                                  networkType: String,
                                  deviceUniqueIdentifier: String,
                                  mobileOperatingSystemName: String,
                                  mobileOperatingSystemVersionName: String,
                                  deviceManufacturerName: String,
                                  deviceModelName: String) {
        val params = AppSessionDatasourceParams(appVersionCode,
                appVersionName,
                networkType,
                deviceUniqueIdentifier,
                mobileOperatingSystemName,
                mobileOperatingSystemVersionName,
                deviceManufacturerName,
                deviceModelName)
        try {
            val appSession = mAppSessionDatasource.createAppSession(params)
            mAppPreferencesDatasource.saveAppSessionCode(appSession.code)
        } catch (e: ApiException) {
            mAppPreferencesDatasource.cleanAppSession()
            if (ErrorCodes.Session.EXPIRED_APP_VERSION == e.apiError?.code) {
                throw ExpiredAppVersionException()
            }
            throw e
        }
    }

    override fun cleanAppSession() {
        mAppPreferencesDatasource.cleanAppSession()
    }

}