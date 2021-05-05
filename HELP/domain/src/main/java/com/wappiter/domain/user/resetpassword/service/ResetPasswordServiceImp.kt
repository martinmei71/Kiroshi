package com.wappiter.domain.user.resetpassword.service

import com.wappiter.domain.base.globalexception.ApiException
import com.wappiter.domain.base.globalexception.MapperException
import com.wappiter.domain.base.globalexception.NetworkException
import com.wappiter.domain.user.resetpassword.data.ResetPasswordDatasource
import com.wappiter.domain.user.resetpassword.data.ResetPasswordDatasourceParams

class ResetPasswordServiceImp(private val mResetPasswordDatasource: ResetPasswordDatasource) : ResetPasswordService {

    @Throws(NetworkException::class, ApiException::class, MapperException::class)
    override fun resetPassword(email: String, code: String, password: String) {
        mResetPasswordDatasource.resetPassword(ResetPasswordDatasourceParams(email, code, password))
    }
}