package com.wappiter.domain.user.loginwithidentity.data

import com.wappiter.domain.base.globalexception.ApiException
import com.wappiter.domain.base.globalexception.MapperException
import com.wappiter.domain.base.globalexception.NetworkException
import com.wappiter.domain.user.UserSession

interface LoginWithIdentityDatasource {

    @Throws(NetworkException::class, ApiException::class, MapperException::class)
    fun loginWithIdentity(params: LoginWithIdentityDatasourceParams): UserSession
}