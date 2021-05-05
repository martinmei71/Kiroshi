package com.wappiter.data.user.session

import com.google.gson.Gson
import com.wappiter.data.apppreferences.PreferencesHelper
import com.wappiter.domain.user.UserSession
import com.wappiter.domain.user.session.UserSessionLocalDatasource

open class UserSessionLocalDatasourceImp(private val preferencesHelper: PreferencesHelper) : UserSessionLocalDatasource {

    private val USER_SESSION_KEY: String = "user_session"

    override fun hasUserSession(): Boolean {
        val userSessionJSON: String? = preferencesHelper.getPref(USER_SESSION_KEY, null)
        return userSessionJSON != null
    }

    override fun getUserSession(): UserSession? {
        val userSessionJSON: String? = preferencesHelper.getPref(USER_SESSION_KEY, null)
        return if (userSessionJSON != null) {
            Gson().fromJson(userSessionJSON, UserSession::class.java)
        } else {
            null
        }
    }

    override fun saveUserSession(user: UserSession) {
        preferencesHelper.savePref(USER_SESSION_KEY, Gson().toJson(user))
    }

    override fun cleanUserSession() {
        preferencesHelper.delete(USER_SESSION_KEY)
    }
}