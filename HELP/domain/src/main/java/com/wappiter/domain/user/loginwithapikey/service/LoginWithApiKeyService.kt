package com.wappiter.domain.user.loginwithapikey.service

import com.wappiter.domain.base.globalexception.ApiException
import com.wappiter.domain.base.globalexception.MapperException
import com.wappiter.domain.base.globalexception.NetworkException
import com.wappiter.domain.user.UserSession
import com.wappiter.domain.user.loginwithapikey.exceptions.UserNotLoggedException


interface LoginWithApiKeyService {

    @Throws(NetworkException::class, ApiException::class, MapperException::class, UserNotLoggedException::class)
    fun loginWithApiKey(): UserSession
}