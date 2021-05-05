package com.wappiter.domain.user.registration.service

import com.wappiter.domain.base.globalexception.ApiException
import com.wappiter.domain.base.globalexception.MapperException
import com.wappiter.domain.base.globalexception.NetworkException
import com.wappiter.domain.user.UserSession

interface RegistrationService {

    @Throws(NetworkException::class, ApiException::class, MapperException::class)
    fun register(firstName: String, lastName: String, email: String, photoUrl: String?, userId: String?, idToken: String?, providerName: String, password: String?): UserSession
}