package com.wappiter.app.presentation.base

import me.panavtec.threaddecoratedview.views.ThreadSpec
import me.panavtec.threaddecoratedview.views.ViewInjector

class CustomViewInjectorImp(private val threadSpec: ThreadSpec) : CustomViewInjector {
    override fun <V> injectView(view: V): V {
        return ViewInjector.inject(view, threadSpec)
    }

    override fun <V> nullObjectPatternView(view: V): V {
        return ViewInjector.nullObjectPatternView(view)
    }

}