package com.wappiter.domain.user.logout.data

import com.wappiter.domain.base.globalexception.ApiException
import com.wappiter.domain.base.globalexception.MapperException
import com.wappiter.domain.base.globalexception.NetworkException

interface LogoutDatasource {
    @Throws(NetworkException::class, ApiException::class, MapperException::class)
    fun logout()
}