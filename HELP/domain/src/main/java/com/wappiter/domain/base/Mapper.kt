package com.wappiter.domain.base

interface Mapper<M, P> {
    fun map(model: M): P
}