package com.wappiter.domain.appsession.interactor

import com.wappiter.domain.appsession.exception.ExpiredAppVersionException
import com.wappiter.domain.appsession.service.AppSessionService
import com.wappiter.domain.base.globalexception.BaseException
import com.wappiter.domain.base.interactor.Interactor
import com.wappiter.domain.base.interactor.InteractorResponse
import com.wappiter.domain.base.interactorerror.ExpiredAppVersionInteractorError
import com.wappiter.infrastructure.interactor.InteractorErrorHandler

class AppSessionInteractor(private val mAppSessionService: AppSessionService,
                           private val mInteractorErrorHandler: InteractorErrorHandler) : Interactor<InteractorResponse<Void>> {
    private lateinit var mValues: AppSessionInteractorValues
    fun setInteractorValues(values: AppSessionInteractorValues) {
        mValues = values
    }

    override fun call(): InteractorResponse<Void> {

        try {
            mAppSessionService.createAppSession(mValues.appVersionCode,
                    mValues.appVersionName,
                    mValues.networkType,
                    mValues.deviceUniqueIdentifier,
                    mValues.mobileOperatingSystemName,
                    mValues.mobileOperatingSystemVersionName,
                    mValues.deviceManufacturerName,
                    mValues.deviceModelName)
            return InteractorResponse()
        } catch (e: ExpiredAppVersionException) {
            e.printStackTrace()
            return InteractorResponse(ExpiredAppVersionInteractorError())
        } catch (e: BaseException) {
            @Suppress("UNCHECKED_CAST")
            return mInteractorErrorHandler.handle(e) as InteractorResponse<Void>
        }
    }
}