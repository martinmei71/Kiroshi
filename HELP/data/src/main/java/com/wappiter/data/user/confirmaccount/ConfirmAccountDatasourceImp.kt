package com.wappiter.data.user.confirmaccount

import com.wappiter.data.ApiRetrofit
import com.wappiter.data.base.BaseNetworkDatasourceParamsImp
import com.wappiter.data.user.UserApiModel
import com.wappiter.domain.base.Mapper
import com.wappiter.domain.base.globalexception.MapperException
import com.wappiter.domain.base.globalexception.NetworkException
import com.wappiter.domain.user.UserSession
import com.wappiter.domain.user.confirmaccount.data.ConfirmAccountDatasource
import com.wappiter.domain.user.confirmaccount.data.ConfirmAccountDatasourceParams

import retrofit2.Call

class ConfirmAccountDatasourceImp(val retrofit: ApiRetrofit, mapper: Mapper<UserApiModel, UserSession>) : BaseNetworkDatasourceParamsImp<ConfirmAccountDatasourceParams, UserApiModel, UserSession>(mapper), ConfirmAccountDatasource {

    @Throws(NetworkException::class, MapperException::class)
    override fun confirm(params: ConfirmAccountDatasourceParams): UserSession {
        return execute(params)
    }

    override fun run(datasourceParams: ConfirmAccountDatasourceParams): Call<UserApiModel> {
        return retrofit.confirm(datasourceParams.email, datasourceParams.code)
    }
}