package com.wappiter.domain.base

/**
 * Created by porta on 30/08/2017.
 */
class SimpleResponseModel(val isSuccess: Boolean,
                          private val message: String?,
                          private val errors: String?) : Model {

    fun getMessage(): String {
        return message ?: "no message"
    }

    val error: String
        get() = errors ?: "no errors"

}