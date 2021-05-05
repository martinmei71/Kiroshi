package com.wappiter.app.infrastucture.domain.outputs

import android.os.Handler
import me.panavtec.threaddecoratedview.views.ThreadSpec

class MainThreadSpec : ThreadSpec {
    var handler = Handler()
    override fun execute(action: Runnable) {
        handler.post(action)
    }
}