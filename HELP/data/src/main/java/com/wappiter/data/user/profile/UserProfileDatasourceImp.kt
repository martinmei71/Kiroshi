package com.wappiter.data.user.profile

import com.wappiter.data.ApiRetrofit
import com.wappiter.data.base.BaseNetworkDatasourceParamsImp
import com.wappiter.data.user.UserApiModel
import com.wappiter.domain.base.Mapper
import com.wappiter.domain.base.globalexception.ApiException
import com.wappiter.domain.base.globalexception.MapperException
import com.wappiter.domain.base.globalexception.NetworkException
import com.wappiter.domain.user.UserSession
import com.wappiter.domain.user.profile.data.UserProfileDatasource
import com.wappiter.domain.user.profile.data.UserProfileDatasourceParams
import retrofit2.Call

class UserProfileDatasourceImp(val retrofit: ApiRetrofit,
                               mapper: Mapper<UserApiModel, UserSession>) : BaseNetworkDatasourceParamsImp<UserProfileDatasourceParams, UserApiModel, UserSession>(mapper), UserProfileDatasource {

    @Throws(NetworkException::class, ApiException::class, MapperException::class)
    override fun getUserProfile(params: UserProfileDatasourceParams): UserSession {
        return execute(params)
    }

    override fun run(datasourceParams: UserProfileDatasourceParams): Call<UserApiModel> {
        return retrofit.getUserProfile(datasourceParams.apiKey)
    }
}