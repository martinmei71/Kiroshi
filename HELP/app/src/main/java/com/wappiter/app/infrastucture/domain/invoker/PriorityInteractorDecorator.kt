package com.wappiter.app.infrastucture.domain.invoker

import com.wappiter.app.presentation.base.invoker.InteractorExecution
import java.util.concurrent.Callable

class PriorityInteractorDecorator<T>(private val execution: InteractorExecution<T>) : Callable<T>, PriorizableInteractor {
    override fun call(): T {
        return execution.interactor.call() as T
    }

    override val priority: Int
        get() = execution.priority

    override val description: String
        get() = execution.javaClass.toString()

}