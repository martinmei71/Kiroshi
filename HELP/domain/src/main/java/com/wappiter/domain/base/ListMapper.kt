package com.wappiter.domain.base

import java.util.*

class ListMapper<M, P>(private val mapper: Mapper<M, P>) : Mapper<List<M>?, List<P>> {
    override fun map(models: List<M>?): List<P> {
        val result = ArrayList<P>()
        if (models != null) {
            for (model in models) {
                result.add(mapper.map(model))
            }
        }

        return result.toList()
    }

}