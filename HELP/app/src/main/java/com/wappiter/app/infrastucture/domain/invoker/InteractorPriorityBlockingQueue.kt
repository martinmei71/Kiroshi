package com.wappiter.app.infrastucture.domain.invoker

import java.util.*
import java.util.concurrent.PriorityBlockingQueue

class InteractorPriorityBlockingQueue(initialCapacity: Int) : PriorityBlockingQueue<Runnable?>(initialCapacity, Comparator { runnable1, runnable2 ->
    val firstIsPriority = runnable1 is PriorityRunnableFutureDecorated<*>
    val secondIsPriority = runnable2 is PriorityRunnableFutureDecorated<*>
    if (!firstIsPriority && !secondIsPriority) {
        return@Comparator 0
    } else if (!firstIsPriority) {
        return@Comparator -1
    } else if (!secondIsPriority) {
        return@Comparator 1
    }
    val priority1 = (runnable1 as PriorityRunnableFutureDecorated<*>).priority
    val priority2 = (runnable2 as PriorityRunnableFutureDecorated<*>).priority
    Integer.valueOf(priority1).compareTo(priority2)
})