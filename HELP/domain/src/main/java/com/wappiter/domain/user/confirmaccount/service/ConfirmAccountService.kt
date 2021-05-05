package com.wappiter.domain.user.confirmaccount.service

import com.wappiter.domain.base.globalexception.ApiException
import com.wappiter.domain.base.globalexception.MapperException
import com.wappiter.domain.base.globalexception.NetworkException

interface ConfirmAccountService {
    @Throws(NetworkException::class, ApiException::class, MapperException::class)
    fun confirm(code: String)
}