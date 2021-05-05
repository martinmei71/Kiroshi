package com.wappiter.domain.user.requestcode.service

import com.wappiter.domain.base.globalexception.ApiException
import com.wappiter.domain.base.globalexception.MapperException
import com.wappiter.domain.base.globalexception.NetworkException
import com.wappiter.domain.user.requestcode.data.RequestConfirmationCodeDatasource
import com.wappiter.domain.user.requestcode.data.RequestConfirmationCodeDatasourceParams
import com.wappiter.domain.user.session.UserSessionLocalDatasource

class RequestConfirmationCodeServiceImp(private val mRequestConfirmationCodeDatasource: RequestConfirmationCodeDatasource,
                                        private val mUserSessionLocalDatasource: UserSessionLocalDatasource) : RequestConfirmationCodeService {

    @Throws(NetworkException::class, ApiException::class, MapperException::class)
    override fun requestConfirmationCode() {
        val userSession = mUserSessionLocalDatasource.getUserSession()
        mRequestConfirmationCodeDatasource.requestConfirmationCode(RequestConfirmationCodeDatasourceParams(userSession!!.user.email))
    }
}