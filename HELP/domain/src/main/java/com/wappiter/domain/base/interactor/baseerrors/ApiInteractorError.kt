package com.wappiter.domain.base.interactor.baseerrors

import com.wappiter.domain.base.globalexception.ApiError

class ApiInteractorError(val httpCode: Int, val apiError: ApiError) : BaseInteractorError(API_EXCEPTION)