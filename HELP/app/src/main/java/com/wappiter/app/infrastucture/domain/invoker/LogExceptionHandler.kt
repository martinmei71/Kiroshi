package com.wappiter.app.infrastucture.domain.invoker

import android.util.Log

class LogExceptionHandler : Thread.UncaughtExceptionHandler {
    override fun uncaughtException(thread: Thread,
                                   ex: Throwable) {
        Log.e(TAG, "Unhandled Interactor Exception", ex)
    }

    companion object {
        private const val TAG = "LogExceptionHandler"
    }
}