package com.wappiter.data.user

import com.wappiter.domain.base.Mapper
import com.wappiter.domain.user.User
import com.wappiter.domain.user.UserSession

class UserApiModelSessionMapper : Mapper<UserApiModel, UserSession> {

    override fun map(model: UserApiModel): UserSession {
        return UserSession(model.apiKey, User(model.email, model.locale, model.hasFavourites, model.hasMissingData))
    }
}