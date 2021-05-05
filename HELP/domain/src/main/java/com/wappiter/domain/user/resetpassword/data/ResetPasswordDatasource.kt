package com.wappiter.domain.user.resetpassword.data

import com.wappiter.domain.base.VoidModel
import com.wappiter.domain.base.globalexception.ApiException
import com.wappiter.domain.base.globalexception.MapperException
import com.wappiter.domain.base.globalexception.NetworkException

interface ResetPasswordDatasource {

    @Throws(NetworkException::class, ApiException::class, MapperException::class)
    fun resetPassword(params: ResetPasswordDatasourceParams): VoidModel
}