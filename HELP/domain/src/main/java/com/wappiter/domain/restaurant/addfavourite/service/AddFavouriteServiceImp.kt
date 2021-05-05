package com.wappiter.domain.restaurant.addfavourite.service

import com.wappiter.domain.base.VoidModel
import com.wappiter.domain.restaurant.addfavourite.data.AddFavouriteDatasource
import com.wappiter.domain.restaurant.addfavourite.data.AddFavouriteDatasourceParams

class AddFavouriteServiceImp(private val addFavouriteDatasource: AddFavouriteDatasource) : AddFavouriteService {

    override fun add(restaurantId: String): VoidModel {
        return addFavouriteDatasource.add(AddFavouriteDatasourceParams(restaurantId))
    }
}