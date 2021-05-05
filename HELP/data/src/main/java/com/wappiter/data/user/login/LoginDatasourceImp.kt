package com.wappiter.data.user.login

import com.wappiter.data.ApiRetrofit
import com.wappiter.data.base.BaseNetworkDatasourceParamsImp
import com.wappiter.data.user.UserApiModel
import com.wappiter.domain.base.Mapper
import com.wappiter.domain.base.globalexception.ApiException
import com.wappiter.domain.base.globalexception.MapperException
import com.wappiter.domain.base.globalexception.NetworkException
import com.wappiter.domain.user.UserSession
import com.wappiter.domain.user.login.data.LoginDatasource
import com.wappiter.domain.user.login.data.LoginDatasourceParams
import retrofit2.Call

class LoginDatasourceImp(var retrofit: ApiRetrofit, mapper: Mapper<UserApiModel, UserSession>) : BaseNetworkDatasourceParamsImp<LoginDatasourceParams, UserApiModel, UserSession>(mapper), LoginDatasource {

    @Throws(NetworkException::class, ApiException::class, MapperException::class)
    override fun login(params: LoginDatasourceParams): UserSession {
        return execute(params)
    }

    override fun run(datasourceParams: LoginDatasourceParams): Call<UserApiModel> {
        return retrofit.loginWithEmail(datasourceParams.email, datasourceParams.password)
    }
}