package com.wappiter.app.presentation.base

interface CustomViewInjector {
    fun <V> injectView(view: V): V
    fun <V> nullObjectPatternView(view: V): V
}