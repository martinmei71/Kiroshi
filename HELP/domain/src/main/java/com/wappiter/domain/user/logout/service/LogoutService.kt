package com.wappiter.domain.user.logout.service

import com.wappiter.domain.base.Service
import com.wappiter.domain.base.globalexception.ApiException
import com.wappiter.domain.base.globalexception.MapperException
import com.wappiter.domain.base.globalexception.NetworkException

interface LogoutService : Service {
    @Throws(NetworkException::class, ApiException::class, MapperException::class)
    fun logoutUser()
}