package com.wappiter.domain.user.session.service

import com.wappiter.domain.base.Service
import com.wappiter.domain.base.globalexception.ApiException
import com.wappiter.domain.user.UserSession

interface UserSessionService : Service {
    @Throws(ApiException::class)
    fun getLoggedUser(): UserSession

}