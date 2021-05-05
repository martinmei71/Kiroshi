package com.wappiter.domain.base.interactor

import java.util.concurrent.Callable

interface Interactor<T : InteractorResponse<*>?> : Callable<T> {
    override fun call(): T
}