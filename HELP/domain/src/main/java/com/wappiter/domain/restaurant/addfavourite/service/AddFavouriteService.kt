package com.wappiter.domain.restaurant.addfavourite.service

import com.wappiter.domain.base.VoidModel
import com.wappiter.domain.base.globalexception.ApiException
import com.wappiter.domain.base.globalexception.MapperException
import com.wappiter.domain.base.globalexception.NetworkException

interface AddFavouriteService {
    @Throws(NetworkException::class, ApiException::class, MapperException::class)
    fun add(restaurantId: String): VoidModel
}