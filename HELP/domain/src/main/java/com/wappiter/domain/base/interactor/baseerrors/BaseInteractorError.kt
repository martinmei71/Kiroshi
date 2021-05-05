package com.wappiter.domain.base.interactor.baseerrors

/**
 * Created by porta on 22/08/2017.
 */
open class BaseInteractorError(override var type: Int) : InteractorError {

    companion object {
        const val API_EXCEPTION = 1
        const val NETWORK_EXCEPTION = 2
        const val MAPPER_EXCEPTION = 3
        const val DOMAIN_EXCEPTION = 4
        const val INFRASTRUCTURE_EXCEPTION = 5
    }
}