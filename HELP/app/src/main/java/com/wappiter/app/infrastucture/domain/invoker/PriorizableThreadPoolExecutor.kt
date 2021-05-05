package com.wappiter.app.infrastucture.domain.invoker

import java.util.concurrent.*

class PriorizableThreadPoolExecutor(corePoolSize: Int,
                                    maximumPoolSize: Int,
                                    keepAliveTime: Long,
                                    unit: TimeUnit?,
                                    workQueue: BlockingQueue<Runnable?>?,
                                    threadFactory: ThreadFactory?) : ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory) {
    override fun <T> newTaskFor(callable: Callable<T>): RunnableFuture<T> {
        val runnableFuture = super.newTaskFor(callable)
        return decorateFuture(runnableFuture, getCandidatePriority(callable))
    }

    override fun <T> newTaskFor(runnable: Runnable,
                                value: T): RunnableFuture<T> {
        val runnableFuture = super.newTaskFor(runnable, value)
        return decorateFuture(runnableFuture, getCandidatePriority(runnable))
    }

    private fun getCandidatePriority(callable: Any): Int {
        return if (callable is PriorizableInteractor) callable.priority else 0
    }

    private fun <T> decorateFuture(runnableFuture: RunnableFuture<T>,
                                   priority: Int): RunnableFuture<T> {
        return PriorityRunnableFutureDecorated(runnableFuture, priority)
    }
}