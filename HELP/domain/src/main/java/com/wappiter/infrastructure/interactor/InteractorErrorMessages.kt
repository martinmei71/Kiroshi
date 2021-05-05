package com.wappiter.infrastructure.interactor

/**
 * Created by porta on 16/11/17.
 */
interface InteractorErrorMessages {
    fun invalidUserName(): String?
    fun invalidPassword(): String?
    fun networkError(): String?
    fun mapperError(): String?
}