package com.wappiter.app.infrastucture.di.modules

import com.wappiter.app.BuildConfig
import com.wappiter.app.infrastucture.domain.invoker.*
import com.wappiter.app.presentation.base.invoker.InteractorInvoker
import dagger.Module
import dagger.Provides
import java.util.concurrent.ExecutorService
import java.util.concurrent.ThreadFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class DomainModule {
    @Provides
    @Singleton
    fun provideInteractorInvoker(executor: ExecutorService,
                                 logExceptionHandler: LogExceptionHandler): InteractorInvoker {
        return InteractorInvokerImp(executor, logExceptionHandler)
    }

    @Provides
    @Singleton
    fun provideLogExceptionHandler(): LogExceptionHandler {
        return LogExceptionHandler()
    }

    @Provides
    @Singleton
    fun provideExecutor(threadFactory: ThreadFactory?,
                        blockingQueue: InteractorPriorityBlockingQueue?): ExecutorService {
        return PriorizableThreadPoolExecutor(BuildConfig.CONCURRENT_INTERACTORS,
                BuildConfig.CONCURRENT_INTERACTORS,
                0L,
                TimeUnit.MILLISECONDS,
                blockingQueue,
                threadFactory)
    }

    @Provides
    @Singleton
    fun provideBlockingQueue(): InteractorPriorityBlockingQueue {
        return InteractorPriorityBlockingQueue(100)
    }

    @Provides
    @Singleton
    fun provideThreadFactory(): ThreadFactory {
        return InteractorOutputThreadFactory()
    }
}