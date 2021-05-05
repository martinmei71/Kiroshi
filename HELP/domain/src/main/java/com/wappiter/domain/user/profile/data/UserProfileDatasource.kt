package com.wappiter.domain.user.profile.data

import com.wappiter.domain.base.globalexception.ApiException
import com.wappiter.domain.base.globalexception.MapperException
import com.wappiter.domain.base.globalexception.NetworkException
import com.wappiter.domain.user.UserSession

interface UserProfileDatasource {

    @Throws(NetworkException::class, ApiException::class, MapperException::class)
    fun getUserProfile(params: UserProfileDatasourceParams): UserSession
}