package com.wappiter.data.user.resetpassword

import com.wappiter.data.ApiRetrofit
import com.wappiter.data.base.BaseNetworkDatasourceParamsImp
import com.wappiter.data.base.mapper.ApiVoid
import com.wappiter.domain.base.VoidModel
import com.wappiter.domain.base.globalexception.ApiException
import com.wappiter.domain.base.globalexception.MapperException
import com.wappiter.domain.base.globalexception.NetworkException
import com.wappiter.domain.user.resetpassword.data.ResetPasswordDatasource
import com.wappiter.domain.user.resetpassword.data.ResetPasswordDatasourceParams
import retrofit2.Call

class ResetPasswordDatasourceImp(val retrofit: ApiRetrofit) : BaseNetworkDatasourceParamsImp<ResetPasswordDatasourceParams, ApiVoid, VoidModel>(), ResetPasswordDatasource {

    @Throws(NetworkException::class, ApiException::class, MapperException::class)
    override fun resetPassword(params: ResetPasswordDatasourceParams): VoidModel {
        return execute(params)
    }

    override fun run(datasourceParams: ResetPasswordDatasourceParams): Call<ApiVoid> {
        return retrofit.resetPassword(datasourceParams.email, datasourceParams.code, datasourceParams.password)
    }
}