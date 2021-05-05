package com.wappiter.data.restaurant.addfavourite

import com.wappiter.data.ApiRetrofit
import com.wappiter.data.base.BaseNetworkDatasourceParamsImp
import com.wappiter.data.base.mapper.ApiVoid
import com.wappiter.domain.base.Mapper
import com.wappiter.domain.base.VoidModel
import com.wappiter.domain.base.globalexception.ApiException
import com.wappiter.domain.base.globalexception.MapperException
import com.wappiter.domain.base.globalexception.NetworkException
import com.wappiter.domain.restaurant.addfavourite.data.AddFavouriteDatasource
import com.wappiter.domain.restaurant.addfavourite.data.AddFavouriteDatasourceParams
import retrofit2.Call

class AddFavouriteDatasourceImp(val retrofit: ApiRetrofit, mapper: Mapper<ApiVoid, VoidModel>) :
        BaseNetworkDatasourceParamsImp<AddFavouriteDatasourceParams, ApiVoid, VoidModel>(mapper), AddFavouriteDatasource {

    @Throws(NetworkException::class, ApiException::class, MapperException::class)
    override fun add(params: AddFavouriteDatasourceParams): VoidModel {
        return execute(params)
    }

    override fun run(datasourceParams: AddFavouriteDatasourceParams): Call<ApiVoid> {
        return retrofit.addFavourite(datasourceParams.restaurantId)
    }
}