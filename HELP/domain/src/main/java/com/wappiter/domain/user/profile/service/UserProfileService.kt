package com.wappiter.domain.user.profile.service

import com.wappiter.domain.base.globalexception.ApiException
import com.wappiter.domain.base.globalexception.MapperException
import com.wappiter.domain.base.globalexception.NetworkException
import com.wappiter.domain.user.UserSession

interface UserProfileService {

    @Throws(NetworkException::class, ApiException::class, MapperException::class)
    fun getUser(): UserSession
}