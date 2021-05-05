package com.wappiter.data.base.error.handler

import com.wappiter.data.base.error.ApiErrorHandler
import com.wappiter.domain.base.globalexception.ApiException
import com.wappiter.domain.base.globalexception.MapperException
import com.wappiter.domain.base.globalexception.NetworkException
import retrofit2.Response

open class CompositeErrorHandler : ApiErrorHandler {

    protected var handlers: List<ApiErrorHandler> = emptyList()

    open fun CompositeErrorHandler(handlers: List<ApiErrorHandler>) {
        this.handlers = handlers
    }

    override fun canHandle(response: Response<*>): Boolean {
        for (handler in handlers) {
            if (handler.canHandle(response)) {
                return true
            }
        }
        return false
    }

    @Throws(ApiException::class, NetworkException::class, MapperException::class)
    override fun handle(response: Response<*>) {
        for (handler in handlers) {
            if (handler.canHandle(response)) {
                handler.handle(response)
            }
        }
    }

}