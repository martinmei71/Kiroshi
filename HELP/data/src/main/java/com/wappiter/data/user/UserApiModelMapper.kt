package com.wappiter.data.user

import com.wappiter.domain.base.Mapper
import com.wappiter.domain.user.User

class UserApiModelMapper : Mapper<UserApiModel, User> {

    override fun map(model: UserApiModel): User {
        return User(model.email, model.locale, model.hasFavourites, model.hasMissingData) //TODO cambiar cuando venga del servidor
    }
}