package com.wappiter.domain.user.profile.service

import com.wappiter.domain.base.globalexception.ApiException
import com.wappiter.domain.base.globalexception.MapperException
import com.wappiter.domain.base.globalexception.NetworkException
import com.wappiter.domain.user.UserSession
import com.wappiter.domain.user.loginwithapikey.exceptions.UserNotLoggedException
import com.wappiter.domain.user.profile.data.UserProfileDatasource
import com.wappiter.domain.user.profile.data.UserProfileDatasourceParams
import com.wappiter.domain.user.session.UserSessionLocalDatasource

class UserProfileServiceImp(private val mUserProfileDatasource: UserProfileDatasource,
                            private val mUserSessionLocalDatasource: UserSessionLocalDatasource) : UserProfileService {

    @Throws(NetworkException::class, ApiException::class, MapperException::class)
    override fun getUser(): UserSession {
        if (!mUserSessionLocalDatasource.hasUserSession()) {
            throw UserNotLoggedException()
        } else {
            val userSessionSaved = mUserSessionLocalDatasource.getUserSession()
            val userSession = mUserProfileDatasource.getUserProfile(UserProfileDatasourceParams(userSessionSaved!!.apiKey))
            mUserSessionLocalDatasource.saveUserSession(userSession)
            return userSession
        }
    }
}