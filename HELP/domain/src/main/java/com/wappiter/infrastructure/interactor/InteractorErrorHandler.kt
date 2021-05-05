package com.wappiter.infrastructure.interactor

import com.wappiter.domain.base.globalexception.BaseException
import com.wappiter.domain.base.interactor.InteractorResponse

/**
 * Created by porta on 22/11/17.
 */
interface InteractorErrorHandler {
    fun handle(exception: BaseException): InteractorResponse<*>
}