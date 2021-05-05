package com.wappiter.data.base.error.handler

import com.wappiter.data.base.error.ApiErrorHandler
import java.util.*

class DatasourceErrorHandler() : CompositeErrorHandler() {

    init {
        super.handlers = buildHandlers()
    }

    private fun buildHandlers(): ArrayList<ApiErrorHandler> {
        val datasourceErrorHandlers = ArrayList<ApiErrorHandler>()
        datasourceErrorHandlers.add(StatusCodeErrorHandler())
        datasourceErrorHandlers.add(NetworkErrorHandler())
        return datasourceErrorHandlers
    }
}