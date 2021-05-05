package com.wappiter.domain.base.globalexception

class BaseInnerException(private val code: String?,
                         private val message: String?) {
    private var userFriendlyMessage: String? = null

    constructor(code: String?,
                message: String?,
                userFriendlyMessage: String?) : this(code, message) {
        this.userFriendlyMessage = userFriendlyMessage
    }

    fun getCode(): String {
        return code ?: ""
    }

    fun getMessage(): String {
        return message ?: ""
    }

    fun getUserFriendlyMessage(): String {
        return (if (userFriendlyMessage == null) "" else userFriendlyMessage!!)
    }

    override fun toString(): String {
        return "BaseInnerException{code='$code', message='$message', userFriendlyMessage='$userFriendlyMessage'}"
    }

}