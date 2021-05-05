package com.wappiter.data.base

import com.wappiter.data.base.error.handler.DatasourceErrorHandler
import com.wappiter.domain.base.globalexception.ApiException
import com.wappiter.domain.base.globalexception.MapperException
import com.wappiter.domain.base.globalexception.NetworkException
import retrofit2.Response

/**
 * Created by porta on 21/08/2017.
 */
abstract class BaseNetworkDatasource {

    @Throws(NetworkException::class, MapperException::class, ApiException::class)
    protected open fun handleError(response: Response<*>) {
        val datasourceErrorHandler = DatasourceErrorHandler()
        datasourceErrorHandler.handle(response)
    }
}