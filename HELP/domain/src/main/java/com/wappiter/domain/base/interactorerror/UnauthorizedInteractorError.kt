package com.wappiter.domain.base.interactorerror

import com.wappiter.domain.base.globalexception.ApiError
import com.wappiter.domain.base.interactor.baseerrors.BaseInteractorError

/**
 * Created by porta on 16/11/17.
 */
class UnauthorizedInteractorError(apiError: ApiError?) : BaseInteractorError(API_EXCEPTION)