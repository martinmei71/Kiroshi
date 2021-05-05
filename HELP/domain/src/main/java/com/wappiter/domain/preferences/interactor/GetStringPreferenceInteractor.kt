package com.wappiter.domain.preferences.interactor

import com.wappiter.domain.base.globalexception.BaseException
import com.wappiter.domain.base.interactor.Interactor
import com.wappiter.domain.base.interactor.InteractorResponse
import com.wappiter.domain.preferences.service.PreferencesService
import com.wappiter.infrastructure.interactor.InteractorErrorHandler

/**
 * Created by porta on 09/12/2018.
 */
class GetStringPreferenceInteractor(private val mService: PreferencesService,
                                    private val mInteractorErrorHandler: InteractorErrorHandler) : Interactor<InteractorResponse<String?>?> {
    private var mValues: GetPreferenceInteractorValues? = null
    fun setInteractorValues(values: GetPreferenceInteractorValues?) {
        mValues = values
    }

    override fun call(): InteractorResponse<String?> {
        return try {
            InteractorResponse(mService.getString(mValues!!.key))
        } catch (e: BaseException) {
            mInteractorErrorHandler.handle(e) as InteractorResponse<String?>
        }
    }

}