package com.wappiter.domain.user.requestresetpassword.service

import com.wappiter.domain.base.globalexception.ApiException
import com.wappiter.domain.base.globalexception.MapperException
import com.wappiter.domain.base.globalexception.NetworkException
import com.wappiter.domain.user.requestresetpassword.data.RequestResetPasswordDatasource
import com.wappiter.domain.user.requestresetpassword.data.RequestResetPasswordDatasourceParams

class RequestResetPasswordServiceImp(private var mRequestResetPasswordDatasource: RequestResetPasswordDatasource) : RequestResetPasswordService {
    @Throws(NetworkException::class, ApiException::class, MapperException::class)
    override fun requestResetPassword(email: String) {
        mRequestResetPasswordDatasource.requestResetPassword(RequestResetPasswordDatasourceParams(email))
    }
}