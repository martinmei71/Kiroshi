package com.wappiter.app.infrastucture.domain.invoker

import java.util.concurrent.ThreadFactory

class InteractorOutputThreadFactory : ThreadFactory {
    override fun newThread(r: Runnable): Thread {
        return Thread(r, "InteractorInvoker$r")
    }
}