package com.wappiter.domain.user.login.service

import com.wappiter.domain.base.globalexception.ApiException
import com.wappiter.domain.base.globalexception.MapperException
import com.wappiter.domain.base.globalexception.NetworkException
import com.wappiter.domain.user.UserSession
import com.wappiter.domain.user.login.data.LoginDatasource
import com.wappiter.domain.user.login.data.LoginDatasourceParams
import com.wappiter.domain.user.session.UserSessionLocalDatasource

class LoginServiceImp(private var loginDatasource: LoginDatasource, var userSessionLocalDatasource: UserSessionLocalDatasource) : LoginService {

    @Throws(NetworkException::class, ApiException::class, MapperException::class)
    override fun login(email: String, password: String): UserSession {

        val userSession: UserSession = loginDatasource.login(LoginDatasourceParams(email, password))
        userSessionLocalDatasource.saveUserSession(userSession)
        return userSession
    }
}