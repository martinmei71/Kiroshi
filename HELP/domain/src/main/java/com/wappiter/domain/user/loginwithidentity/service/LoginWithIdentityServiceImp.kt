package com.wappiter.domain.user.loginwithidentity.service

import com.wappiter.domain.base.globalexception.ApiException
import com.wappiter.domain.base.globalexception.MapperException
import com.wappiter.domain.base.globalexception.NetworkException
import com.wappiter.domain.user.UserSession
import com.wappiter.domain.user.loginwithidentity.data.LoginWithIdentityDatasource
import com.wappiter.domain.user.loginwithidentity.data.LoginWithIdentityDatasourceParams
import com.wappiter.domain.user.session.UserSessionLocalDatasource

class LoginWithIdentityServiceImp(private var loginWithIdentityDatasource: LoginWithIdentityDatasource,
                                  var userSessionLocalDatasource: UserSessionLocalDatasource) : LoginWithIdentityService {

    @Throws(NetworkException::class, ApiException::class, MapperException::class)
    override fun loginWithIdentity(firstName: String, lastName: String, email: String, photoUrl: String?, userId: String, idToken: String, providerName: String): UserSession {

        val params = LoginWithIdentityDatasourceParams(firstName, lastName, email, photoUrl, userId, idToken, providerName)
        val userSession: UserSession = loginWithIdentityDatasource.loginWithIdentity(params)
        userSessionLocalDatasource.saveUserSession(userSession)
        return userSession
    }
}