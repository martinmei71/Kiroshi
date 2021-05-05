package com.wappiter.data.base.error.handler

import com.wappiter.data.base.error.ApiErrorHandler
import com.wappiter.domain.base.globalexception.ApiException
import com.wappiter.domain.base.globalexception.MapperException
import com.wappiter.domain.base.globalexception.NetworkException
import retrofit2.Response

class NetworkErrorHandler : ApiErrorHandler {
    override fun canHandle(response: Response<*>): Boolean {
        return response.code() == -1
    }

    @Throws(ApiException::class, NetworkException::class, MapperException::class)
    override fun handle(response: Response<*>) {
        throw NetworkException()
    }
}