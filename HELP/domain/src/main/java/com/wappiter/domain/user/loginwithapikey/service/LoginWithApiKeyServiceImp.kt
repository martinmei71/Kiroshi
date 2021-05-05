package com.wappiter.domain.user.loginwithapikey.service

import com.wappiter.domain.base.globalexception.ApiException
import com.wappiter.domain.base.globalexception.MapperException
import com.wappiter.domain.base.globalexception.NetworkException
import com.wappiter.domain.user.UserSession
import com.wappiter.domain.user.loginwithapikey.data.LoginWithApiKeyDatasource
import com.wappiter.domain.user.loginwithapikey.data.LoginWithApiKeyDatasourceParams
import com.wappiter.domain.user.loginwithapikey.exceptions.UserNotLoggedException
import com.wappiter.domain.user.session.UserSessionLocalDatasource


class LoginWithApiKeyServiceImp(private val loginWithApiKeyDatasource: LoginWithApiKeyDatasource,
                                private val userSessionLocalDatasource: UserSessionLocalDatasource) : LoginWithApiKeyService {

    @Throws(NetworkException::class, ApiException::class, MapperException::class, UserNotLoggedException::class)
    override fun loginWithApiKey(): UserSession {
        if (!userSessionLocalDatasource.hasUserSession()) {
            throw UserNotLoggedException()
        } else {
            val userSessionSaved = userSessionLocalDatasource.getUserSession()
            val userSession = loginWithApiKeyDatasource.loginWithApiKey(LoginWithApiKeyDatasourceParams(userSessionSaved!!.apiKey))
            userSessionLocalDatasource.saveUserSession(userSession)
            return userSession
        }
    }
}