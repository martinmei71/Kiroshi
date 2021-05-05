package com.wappiter.domain.base.interactor

import com.wappiter.domain.base.interactor.baseerrors.InteractorError

class InteractorResponse<T> {
    var error: InteractorError? = null
        private set
    var result: T? = null
        private set

    constructor()
    constructor(error: InteractorError?) {
        this.error = error
    }

    constructor(result: T) {
        this.result = result
    }

    fun hasError(): Boolean {
        return error != null
    }
}