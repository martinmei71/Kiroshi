package com.wappiter.data.user.loginwithidentity

import com.wappiter.data.ApiRetrofit
import com.wappiter.data.base.BaseNetworkDatasourceParamsImp
import com.wappiter.data.user.UserApiModel
import com.wappiter.domain.base.Mapper
import com.wappiter.domain.base.globalexception.ApiException
import com.wappiter.domain.base.globalexception.MapperException
import com.wappiter.domain.base.globalexception.NetworkException
import com.wappiter.domain.user.UserSession
import com.wappiter.domain.user.loginwithidentity.data.LoginWithIdentityDatasource
import com.wappiter.domain.user.loginwithidentity.data.LoginWithIdentityDatasourceParams
import retrofit2.Call

class LoginWithIdentityDatasourceImp(var retrofit: ApiRetrofit,
                                     mapper: Mapper<UserApiModel, UserSession>) :
        BaseNetworkDatasourceParamsImp<LoginWithIdentityDatasourceParams, UserApiModel, UserSession>(mapper), LoginWithIdentityDatasource {

    @Throws(NetworkException::class, ApiException::class, MapperException::class)
    override fun loginWithIdentity(params: LoginWithIdentityDatasourceParams): UserSession {
        return execute(params)
    }

    override fun run(datasourceParams: LoginWithIdentityDatasourceParams): Call<UserApiModel> {
        return retrofit.loginWithIdentity(
                datasourceParams.firstName,
                datasourceParams.lastName,
                datasourceParams.email,
                datasourceParams.photoUrl,
                datasourceParams.userId,
                datasourceParams.tokenId,
                datasourceParams.providerName)
    }
}