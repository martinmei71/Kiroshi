package com.wappiter.domain.appsession.data

import com.wappiter.domain.appsession.AppSession
import com.wappiter.domain.base.globalexception.ApiException
import com.wappiter.domain.base.globalexception.MapperException
import com.wappiter.domain.base.globalexception.NetworkException

/**
 * Created by porta on 18/12/18.
 */
interface AppSessionDatasource {
    @Throws(NetworkException::class, ApiException::class, MapperException::class)
    fun createAppSession(values: AppSessionDatasourceParams): AppSession
}