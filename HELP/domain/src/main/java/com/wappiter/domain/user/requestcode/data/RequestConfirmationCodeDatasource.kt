package com.wappiter.domain.user.requestcode.data

import com.wappiter.domain.base.VoidModel
import com.wappiter.domain.base.globalexception.ApiException
import com.wappiter.domain.base.globalexception.MapperException
import com.wappiter.domain.base.globalexception.NetworkException

interface RequestConfirmationCodeDatasource {

    @Throws(NetworkException::class, ApiException::class, MapperException::class)
    fun requestConfirmationCode(params: RequestConfirmationCodeDatasourceParams): VoidModel
}