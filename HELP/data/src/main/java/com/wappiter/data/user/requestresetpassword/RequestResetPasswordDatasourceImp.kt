package com.wappiter.data.user.requestresetpassword

import com.wappiter.data.ApiRetrofit
import com.wappiter.data.base.BaseNetworkDatasourceParamsImp
import com.wappiter.data.base.mapper.ApiVoid
import com.wappiter.domain.base.VoidModel
import com.wappiter.domain.base.globalexception.ApiException
import com.wappiter.domain.base.globalexception.MapperException
import com.wappiter.domain.base.globalexception.NetworkException
import com.wappiter.domain.user.requestresetpassword.data.RequestResetPasswordDatasource
import com.wappiter.domain.user.requestresetpassword.data.RequestResetPasswordDatasourceParams
import retrofit2.Call

class RequestResetPasswordDatasourceImp(val retrofit: ApiRetrofit) : BaseNetworkDatasourceParamsImp<RequestResetPasswordDatasourceParams, ApiVoid, VoidModel>(), RequestResetPasswordDatasource {

    @Throws(NetworkException::class, ApiException::class, MapperException::class)
    override fun requestResetPassword(params: RequestResetPasswordDatasourceParams): VoidModel {
        return execute(params)
    }

    override fun run(datasourceParams: RequestResetPasswordDatasourceParams): Call<ApiVoid> {
        return retrofit.requestResetPasswordCode(datasourceParams.email)
    }
}