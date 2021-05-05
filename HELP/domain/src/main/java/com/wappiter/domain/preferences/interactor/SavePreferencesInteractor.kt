package com.wappiter.domain.preferences.interactor

import com.wappiter.domain.base.globalexception.BaseException
import com.wappiter.domain.base.interactor.Interactor
import com.wappiter.domain.base.interactor.InteractorResponse
import com.wappiter.domain.preferences.service.PreferencesService
import com.wappiter.infrastructure.interactor.InteractorErrorHandler

/**
 * Created by porta on 09/12/2018.
 */
class SavePreferencesInteractor(private val mService: PreferencesService,
                                private val mInteractorErrorHandler: InteractorErrorHandler) : Interactor<InteractorResponse<Void>> {
    private var mValues: SavePreferencesInteractorValues? = null
    fun setInteractorValues(values: SavePreferencesInteractorValues?) {
        mValues = values
    }

    override fun call(): InteractorResponse<Void> {
        try {
            mService.savePreferences(mValues!!.preferences)
        } catch (e: BaseException) {
            return mInteractorErrorHandler.handle(e) as InteractorResponse<Void>
        }
        return InteractorResponse()
    }

}