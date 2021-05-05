package com.wappiter.app.base.extensions

inline fun <T> T.guardElse(block: T.() -> Unit): T {
    if (this == null) block(); return this
}
