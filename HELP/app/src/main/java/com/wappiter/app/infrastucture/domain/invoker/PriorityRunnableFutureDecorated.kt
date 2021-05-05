package com.wappiter.app.infrastucture.domain.invoker

import java.util.concurrent.ExecutionException
import java.util.concurrent.RunnableFuture
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

class PriorityRunnableFutureDecorated<T>(private val undecoratedFuture: RunnableFuture<T>,
                                         val priority: Int) : RunnableFuture<T> {
    override fun run() {
        undecoratedFuture.run()
    }

    override fun cancel(b: Boolean): Boolean {
        return undecoratedFuture.cancel(b)
    }

    override fun isCancelled(): Boolean {
        return undecoratedFuture.isCancelled
    }

    override fun isDone(): Boolean {
        return undecoratedFuture.isDone
    }

    @Throws(InterruptedException::class, ExecutionException::class)
    override fun get(): T {
        return undecoratedFuture.get()
    }

    @Throws(InterruptedException::class, ExecutionException::class, TimeoutException::class)
    override fun get(l: Long,
                     timeUnit: TimeUnit): T {
        return undecoratedFuture.get()
    }

}