package com.wappiter.data.user.registration

import com.wappiter.data.ApiRetrofit
import com.wappiter.data.base.BaseNetworkDatasourceParamsImp
import com.wappiter.data.user.UserApiModel
import com.wappiter.domain.base.Mapper
import com.wappiter.domain.base.globalexception.ApiException
import com.wappiter.domain.base.globalexception.MapperException
import com.wappiter.domain.base.globalexception.NetworkException
import com.wappiter.domain.user.UserSession
import com.wappiter.domain.user.registration.data.RegistrationDatasource
import com.wappiter.domain.user.registration.data.RegistrationDatasourceParams
import retrofit2.Call

class RegistrationDatasourceImp(val retrofit: ApiRetrofit,
                                mapper: Mapper<UserApiModel, UserSession>) : BaseNetworkDatasourceParamsImp<RegistrationDatasourceParams, UserApiModel, UserSession>(mapper), RegistrationDatasource {

    @Throws(NetworkException::class, ApiException::class, MapperException::class)
    override fun register(params: RegistrationDatasourceParams): UserSession {
        return execute(params)
    }

    override fun run(datasourceParams: RegistrationDatasourceParams): Call<UserApiModel> {
        return retrofit.register(
                datasourceParams.firstName,
                datasourceParams.lastName,
                datasourceParams.email,
                datasourceParams.photoUrl,
                datasourceParams.userId,
                datasourceParams.tokenId,
                datasourceParams.providerName,
                datasourceParams.password)
    }
}