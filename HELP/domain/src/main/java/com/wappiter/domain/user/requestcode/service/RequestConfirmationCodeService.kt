package com.wappiter.domain.user.requestcode.service

import com.wappiter.domain.base.globalexception.ApiException
import com.wappiter.domain.base.globalexception.MapperException
import com.wappiter.domain.base.globalexception.NetworkException

interface RequestConfirmationCodeService {

    @Throws(NetworkException::class, ApiException::class, MapperException::class)
    fun requestConfirmationCode()
}