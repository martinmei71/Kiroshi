package com.wappiter.data.base.error

import com.wappiter.domain.base.globalexception.ApiException
import com.wappiter.domain.base.globalexception.MapperException
import com.wappiter.domain.base.globalexception.NetworkException
import retrofit2.Response

interface ApiErrorHandler {
    fun canHandle(response: Response<*>): Boolean

    @Throws(ApiException::class, NetworkException::class, MapperException::class)
    fun handle(response: Response<*>)
}