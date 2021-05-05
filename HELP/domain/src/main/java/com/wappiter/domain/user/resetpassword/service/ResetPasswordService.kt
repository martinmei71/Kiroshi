package com.wappiter.domain.user.resetpassword.service

import com.wappiter.domain.base.globalexception.ApiException
import com.wappiter.domain.base.globalexception.MapperException
import com.wappiter.domain.base.globalexception.NetworkException

interface ResetPasswordService {

    @Throws(NetworkException::class, ApiException::class, MapperException::class)
    fun resetPassword(email: String, code: String, password: String)
}