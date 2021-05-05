package com.wappiter.data.user.loginwithapikey

import com.wappiter.data.ApiRetrofit
import com.wappiter.data.base.BaseNetworkDatasourceParamsImp
import com.wappiter.data.user.UserApiModel
import com.wappiter.domain.base.Mapper
import com.wappiter.domain.base.globalexception.ApiException
import com.wappiter.domain.base.globalexception.MapperException
import com.wappiter.domain.base.globalexception.NetworkException
import com.wappiter.domain.user.UserSession
import com.wappiter.domain.user.loginwithapikey.data.LoginWithApiKeyDatasource
import com.wappiter.domain.user.loginwithapikey.data.LoginWithApiKeyDatasourceParams
import retrofit2.Call

class LoginWithApiKeyDatasourceImp(var retrofit: ApiRetrofit, mapper: Mapper<UserApiModel, UserSession>) : BaseNetworkDatasourceParamsImp<LoginWithApiKeyDatasourceParams, UserApiModel, UserSession>(mapper), LoginWithApiKeyDatasource {

    @Throws(NetworkException::class, ApiException::class, MapperException::class)
    override fun loginWithApiKey(params: LoginWithApiKeyDatasourceParams): UserSession {
        return execute(params)
    }

    override fun run(datasourceParams: LoginWithApiKeyDatasourceParams): Call<UserApiModel> {
        return retrofit.loginWithApiKey(datasourceParams.apiKey)
    }
}