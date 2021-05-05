package com.wappiter.domain.restaurant.addfavourite.data

import com.wappiter.domain.base.VoidModel
import com.wappiter.domain.base.globalexception.ApiException
import com.wappiter.domain.base.globalexception.MapperException
import com.wappiter.domain.base.globalexception.NetworkException

interface AddFavouriteDatasource {
    @Throws(NetworkException::class, ApiException::class, MapperException::class)
    fun add(params: AddFavouriteDatasourceParams): VoidModel
}