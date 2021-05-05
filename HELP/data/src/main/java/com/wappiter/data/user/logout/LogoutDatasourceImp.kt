package com.wappiter.data.user.logout

import com.wappiter.data.ApiRetrofit
import com.wappiter.data.base.BaseNetworkDatasourceImp
import com.wappiter.data.base.mapper.ApiVoid
import com.wappiter.domain.base.Mapper
import com.wappiter.domain.base.VoidModel
import com.wappiter.domain.base.globalexception.ApiException
import com.wappiter.domain.base.globalexception.MapperException
import com.wappiter.domain.base.globalexception.NetworkException
import com.wappiter.domain.user.logout.data.LogoutDatasource
import retrofit2.Call

class LogoutDatasourceImp(val retrofit: ApiRetrofit, mapper: Mapper<ApiVoid, VoidModel>) : BaseNetworkDatasourceImp<ApiVoid, VoidModel>(mapper), LogoutDatasource {

    @Throws(NetworkException::class, ApiException::class, MapperException::class)
    override fun logout() {
        execute()
    }

    override fun run(): Call<ApiVoid> {
        return retrofit.logout()
    }
}