package com.wappiter.domain.user.login.data

import com.wappiter.domain.base.globalexception.ApiException
import com.wappiter.domain.base.globalexception.MapperException
import com.wappiter.domain.base.globalexception.NetworkException
import com.wappiter.domain.user.UserSession

interface LoginDatasource {

    @Throws(NetworkException::class, ApiException::class, MapperException::class)
    fun login(params: LoginDatasourceParams): UserSession
}