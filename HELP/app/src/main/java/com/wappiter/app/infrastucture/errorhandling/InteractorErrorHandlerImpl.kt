package com.wappiter.app.infrastucture.errorhandling

import com.wappiter.domain.base.globalexception.*
import com.wappiter.domain.base.interactor.InteractorResponse
import com.wappiter.domain.base.interactor.baseerrors.*
import com.wappiter.domain.base.interactorerror.UnauthorizedInteractorError
import com.wappiter.domain.base.interactorerror.UserNotLoggedInteractorError
import com.wappiter.domain.user.loginwithapikey.exceptions.UserNotLoggedException
import com.wappiter.infrastructure.exceptions.ErrorCodes
import com.wappiter.infrastructure.interactor.InteractorErrorHandler
import java.util.concurrent.TimeoutException

/**
 * Created by porta on 22/11/17.
 */
class InteractorErrorHandlerImpl : InteractorErrorHandler {
    override fun handle(exception: BaseException): InteractorResponse<*> {
        if (exception is ApiException) {
            val apiException = exception
            if (ErrorCodes.HTML_UNAUTHORIZED == apiException.httpCode.toString()) {
                return InteractorResponse<Any>(UnauthorizedInteractorError(apiException.apiError))
            }
            return if (apiException.apiError != null) {
                InteractorResponse<Any>(ApiInteractorError(apiException.httpCode, apiException.apiError!!))
            } else {
                InteractorResponse<Any>(HttpInteractorError(apiException.httpCode))
            }
        }
        //En muchos interactors se cogen datos del usuario. Si este no existiese se debería enviar al usuario al login
        if (exception is UserNotLoggedException) {
            return InteractorResponse<Any>(UserNotLoggedInteractorError())
        }
        if (exception is TimeoutException) {
            return InteractorResponse<Any>(TimeoutInteractorError())
        }
        if (exception is NetworkException) {
            return InteractorResponse<Any>(NetworkInteractorError())
        }
        if (exception is MapperException) {
            return InteractorResponse<Any>(MapperInteractorError())
        }
        if (exception is InfrastructureException) {
            return InteractorResponse<Any?>(InfrastructureInteractorError(exception))
        }
        if (exception is DomainException) {
            return InteractorResponse<Any?>(DomainInteractorError(exception))
        }
        return InteractorResponse<Any>(DefaultInteractorError())
    }
}