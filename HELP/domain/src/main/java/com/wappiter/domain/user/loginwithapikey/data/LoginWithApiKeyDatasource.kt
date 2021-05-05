package com.wappiter.domain.user.loginwithapikey.data

import com.wappiter.domain.base.globalexception.ApiException
import com.wappiter.domain.base.globalexception.MapperException
import com.wappiter.domain.base.globalexception.NetworkException
import com.wappiter.domain.user.UserSession

interface LoginWithApiKeyDatasource {

    @Throws(NetworkException::class, ApiException::class, MapperException::class)
    fun loginWithApiKey(params: LoginWithApiKeyDatasourceParams): UserSession
}