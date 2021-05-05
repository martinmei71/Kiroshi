package com.wappiter.app.infrastucture.domain.invoker

import com.wappiter.app.presentation.base.invoker.InteractorExecution
import com.wappiter.app.presentation.base.invoker.InteractorInvoker
import java.util.concurrent.ExecutorService
import java.util.concurrent.Future

class InteractorInvokerImp(private val executor: ExecutorService,
                           private val uncaughtExceptionHandler: Thread.UncaughtExceptionHandler) : InteractorInvoker {
    override fun <T> execute(execution: InteractorExecution<T>): Future<T> {
        return if (execution.interactorResult != null) {
            executor
                    .submit(InteractorExecutionFutureTask(execution, uncaughtExceptionHandler)) as Future<T>
        } else {
            executor.submit(PriorityInteractorDecorator(execution))
        }
    }

}