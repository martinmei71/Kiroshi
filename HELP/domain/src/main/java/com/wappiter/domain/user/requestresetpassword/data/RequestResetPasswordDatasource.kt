package com.wappiter.domain.user.requestresetpassword.data

import com.wappiter.domain.base.VoidModel
import com.wappiter.domain.base.globalexception.ApiException
import com.wappiter.domain.base.globalexception.MapperException
import com.wappiter.domain.base.globalexception.NetworkException

interface RequestResetPasswordDatasource {

    @Throws(NetworkException::class, ApiException::class, MapperException::class)
    fun requestResetPassword(params: RequestResetPasswordDatasourceParams): VoidModel
}