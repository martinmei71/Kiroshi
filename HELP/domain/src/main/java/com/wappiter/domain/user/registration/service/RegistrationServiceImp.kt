package com.wappiter.domain.user.registration.service

import com.wappiter.domain.base.globalexception.ApiException
import com.wappiter.domain.base.globalexception.MapperException
import com.wappiter.domain.base.globalexception.NetworkException
import com.wappiter.domain.user.UserSession
import com.wappiter.domain.user.registration.data.RegistrationDatasource
import com.wappiter.domain.user.registration.data.RegistrationDatasourceParams
import com.wappiter.domain.user.session.UserSessionLocalDatasource

class RegistrationServiceImp(private val mRegistrationDatasource: RegistrationDatasource,
                             private val mUserSessionLocalDatasource: UserSessionLocalDatasource) : RegistrationService {
    @Throws(NetworkException::class, ApiException::class, MapperException::class)
    override fun register(firstName: String, lastName: String, email: String, photoUrl: String?, userId: String?, idToken: String?, providerName: String, password: String?): UserSession {
        val userSession = mRegistrationDatasource.register(
                RegistrationDatasourceParams(firstName, lastName, email, photoUrl, userId, idToken, providerName, password))
        mUserSessionLocalDatasource.saveUserSession(userSession)
        return userSession
    }
}