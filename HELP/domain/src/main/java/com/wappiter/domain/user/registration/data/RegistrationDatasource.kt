package com.wappiter.domain.user.registration.data

import com.wappiter.domain.base.globalexception.ApiException
import com.wappiter.domain.base.globalexception.MapperException
import com.wappiter.domain.base.globalexception.NetworkException
import com.wappiter.domain.user.UserSession

interface RegistrationDatasource {

    @Throws(NetworkException::class, ApiException::class, MapperException::class)
    fun register(params: RegistrationDatasourceParams): UserSession
}