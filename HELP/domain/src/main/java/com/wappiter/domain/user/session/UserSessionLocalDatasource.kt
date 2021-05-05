package com.wappiter.domain.user.session

import com.wappiter.domain.user.UserSession

interface UserSessionLocalDatasource {

    fun hasUserSession(): Boolean

    fun getUserSession(): UserSession?

    fun saveUserSession(user: UserSession)

    fun cleanUserSession()
}