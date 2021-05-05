package com.wappiter.app.infrastucture.domain.outputs

import me.panavtec.threaddecoratedview.views.ThreadSpec

class BackThreadSpec : ThreadSpec {
    override fun execute(action: Runnable) {
        Thread(action).start()
    }
}