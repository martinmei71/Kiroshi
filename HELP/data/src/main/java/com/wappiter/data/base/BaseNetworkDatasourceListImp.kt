package com.wappiter.data.base

import com.wappiter.domain.base.ListMapper
import com.wappiter.domain.base.Model
import com.wappiter.domain.base.datasource.VoidDatasourceParams
import com.wappiter.domain.base.globalexception.ApiException
import com.wappiter.domain.base.globalexception.MapperException
import com.wappiter.domain.base.globalexception.NetworkException
import retrofit2.Call

/**
 * Created by porta on 21/08/2017.
 */
abstract class BaseNetworkDatasourceListImp<ApiResponse : ApiModel?, Success : Model?>(mapper: ListMapper<ApiResponse, Success>?) : BaseNetworkDatasourceListParamsImp<VoidDatasourceParams?, ApiResponse, Success>(mapper) {
    protected abstract fun run(): Call<List<ApiResponse>>
    override fun run(aVoid: VoidDatasourceParams?): Call<List<ApiResponse>> {
        return run()
    }

    @Throws(NetworkException::class, ApiException::class, MapperException::class)
    protected fun execute(): List<Success?>? {
        return execute(null)
    }
}