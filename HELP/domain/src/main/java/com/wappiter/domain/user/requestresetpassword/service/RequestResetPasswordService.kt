package com.wappiter.domain.user.requestresetpassword.service

import com.wappiter.domain.base.globalexception.ApiException
import com.wappiter.domain.base.globalexception.MapperException
import com.wappiter.domain.base.globalexception.NetworkException

interface RequestResetPasswordService {

    @Throws(NetworkException::class, ApiException::class, MapperException::class)
    fun requestResetPassword(email: String)
}