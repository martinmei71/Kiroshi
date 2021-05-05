package com.wappiter.data.base

import com.wappiter.domain.base.ListMapper
import com.wappiter.domain.base.Model
import com.wappiter.domain.base.datasource.DatasourceParams
import com.wappiter.domain.base.globalexception.ApiError
import com.wappiter.domain.base.globalexception.ApiException
import com.wappiter.domain.base.globalexception.MapperException
import com.wappiter.domain.base.globalexception.NetworkException
import retrofit2.Call
import retrofit2.Response
import java.io.IOException

/**
 * Created by porta on 21/08/2017.
 */
abstract class BaseNetworkDatasourceListParamsImp<Values : DatasourceParams?, ApiResponse : ApiModel?, ModelObject : Model?>(private val mMapper: ListMapper<ApiResponse, ModelObject>?) : BaseNetworkDatasource() {
    protected abstract fun run(params: Values): Call<List<ApiResponse>>

    @Throws(NetworkException::class, ApiException::class, MapperException::class)
    protected fun execute(params: Values): List<ModelObject>? {
        val response: Response<List<ApiResponse>>
        return try {
            response = run(params).execute()
            /** Returns true if [.code] is in the range [200..300).  */
            if (response.isSuccessful) {
                mMapper?.map(response.body())
            } else {
                if (response.errorBody() != null) {
                    handleError(response)
                }
                throw ApiException(response.code(),
                        ApiError(response.code().toString(), response.message()))
            }
        } catch (e: IOException) {
            e.printStackTrace()
            throw NetworkException()
        }
    }

}