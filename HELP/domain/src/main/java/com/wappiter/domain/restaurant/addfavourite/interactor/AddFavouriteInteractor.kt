package com.wappiter.domain.restaurant.addfavourite.interactor

import com.wappiter.domain.base.VoidModel
import com.wappiter.domain.base.globalexception.BaseException
import com.wappiter.domain.base.interactor.Interactor
import com.wappiter.domain.base.interactor.InteractorResponse
import com.wappiter.domain.restaurant.addfavourite.service.AddFavouriteService
import com.wappiter.infrastructure.interactor.InteractorErrorHandler


class AddFavouriteInteractor(private val addFavouriteService: AddFavouriteService,
                             private val interactorErrorHandler: InteractorErrorHandler) : Interactor<InteractorResponse<VoidModel>> {

    lateinit var mValues: AddFavouritesInteractorValues
    fun setInteractorValues(values: AddFavouritesInteractorValues) {
        mValues = values
    }

    override fun call(): InteractorResponse<VoidModel> {
        try {
            addFavouriteService.add(mValues.restaurantId)
            return InteractorResponse(VoidModel())
        } catch (e: BaseException) {
            @Suppress("UNCHECKED_CAST")
            return interactorErrorHandler.handle(e) as InteractorResponse<VoidModel>
        }
    }
}