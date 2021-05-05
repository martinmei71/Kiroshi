package com.wappiter.data.user.requestcode

import com.wappiter.data.ApiRetrofit
import com.wappiter.data.base.BaseNetworkDatasourceParamsImp
import com.wappiter.data.base.mapper.ApiVoid
import com.wappiter.domain.base.VoidModel
import com.wappiter.domain.base.globalexception.ApiException
import com.wappiter.domain.base.globalexception.MapperException
import com.wappiter.domain.base.globalexception.NetworkException
import com.wappiter.domain.user.requestcode.data.RequestConfirmationCodeDatasource
import com.wappiter.domain.user.requestcode.data.RequestConfirmationCodeDatasourceParams
import retrofit2.Call

class RequestConfirmationCodeDatasourceImp(val retrofit: ApiRetrofit) : BaseNetworkDatasourceParamsImp<RequestConfirmationCodeDatasourceParams, ApiVoid, VoidModel>(), RequestConfirmationCodeDatasource {

    @Throws(NetworkException::class, ApiException::class, MapperException::class)
    override fun requestConfirmationCode(params: RequestConfirmationCodeDatasourceParams): VoidModel {
        return execute(params)
    }

    override fun run(datasourceParams: RequestConfirmationCodeDatasourceParams): Call<ApiVoid> {
        return retrofit.requestConfirmationCode(datasourceParams.email)
    }
}