package com.wappiter.domain.user.confirmaccount.data

import com.wappiter.domain.base.globalexception.ApiException
import com.wappiter.domain.base.globalexception.MapperException
import com.wappiter.domain.base.globalexception.NetworkException
import com.wappiter.domain.user.UserSession

interface ConfirmAccountDatasource {
    @Throws(NetworkException::class, ApiException::class, MapperException::class)
    fun confirm(params: ConfirmAccountDatasourceParams): UserSession
}