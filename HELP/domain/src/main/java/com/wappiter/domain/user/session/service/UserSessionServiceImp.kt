package com.wappiter.domain.user.session.service

import com.wappiter.domain.base.globalexception.ApiException
import com.wappiter.domain.user.UserSession
import com.wappiter.domain.user.loginwithapikey.exceptions.UserNotLoggedException
import com.wappiter.domain.user.session.UserSessionLocalDatasource

class UserSessionServiceImp(private val mUserSessionLocalDatasource: UserSessionLocalDatasource) : UserSessionService {

    @Throws(ApiException::class)
    override fun getLoggedUser(): UserSession {
        return mUserSessionLocalDatasource.getUserSession() ?: throw UserNotLoggedException()
    }
}