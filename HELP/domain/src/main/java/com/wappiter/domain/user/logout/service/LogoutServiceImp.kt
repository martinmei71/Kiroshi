package com.wappiter.domain.user.logout.service

import com.wappiter.domain.base.globalexception.ApiException
import com.wappiter.domain.base.globalexception.MapperException
import com.wappiter.domain.base.globalexception.NetworkException
import com.wappiter.domain.user.logout.data.LogoutDatasource
import com.wappiter.domain.user.session.UserSessionLocalDatasource
import com.wappiter.infrastructure.datasource.AppPreferencesDatasource

class LogoutServiceImp(private val mUserSessionLocalDatasource: UserSessionLocalDatasource, private val mAppPreferencesDatasource: AppPreferencesDatasource,
                       private val mLogoutDatasource: LogoutDatasource) : LogoutService {

    @Throws(NetworkException::class, ApiException::class, MapperException::class)
    override fun logoutUser() {

        try {
            mLogoutDatasource.logout()
        } finally {
            mUserSessionLocalDatasource.cleanUserSession()
            mAppPreferencesDatasource.cleanAppSession()
        }
    }
}