package com.wappiter.domain.base.globalexception

/**
 * Created by porta on 13/11/17.
 */
class ApiException(val httpCode: Int, val apiError: ApiError?) : BaseException()