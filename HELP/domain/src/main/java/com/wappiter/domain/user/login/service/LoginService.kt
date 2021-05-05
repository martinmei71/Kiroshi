package com.wappiter.domain.user.login.service

import com.wappiter.domain.base.globalexception.ApiException
import com.wappiter.domain.base.globalexception.MapperException
import com.wappiter.domain.base.globalexception.NetworkException
import com.wappiter.domain.user.UserSession

interface LoginService {
    @Throws(NetworkException::class, ApiException::class, MapperException::class)
    fun login(email: String, password: String): UserSession
}