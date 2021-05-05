package com.wappiter.app.infrastucture.domain.outputs

import me.panavtec.threaddecoratedview.views.ThreadSpec

class SameThreadSpec : ThreadSpec {
    override fun execute(action: Runnable) {
        action.run()
    }
}