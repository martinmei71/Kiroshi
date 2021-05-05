package com.wappiter.domain.base.globalexception

/**
 * Created by porta on 18/05/17.
 */
/**
 * Created by Adrian Bao on 18/12/18.
 */
/**
 * No extender directamente de esta clase. Para crear exceptions extender o de DomainException o de InfrastructureException (para exceptions del sistema, tipo Error de lectura...)
 */
abstract class BaseException : Exception()
/*
open class BaseException : Exception {
    var httpCode: String? = null
        private set
    private var innerException: BaseInnerException? = null

    constructor()
    constructor(httpCode: String) {
        this.httpCode = httpCode
    }

    constructor(httpCode: String,
                innerException: BaseInnerException) {
        this.httpCode = httpCode
        this.innerException = innerException
    }

    val innerCode: String?
        get() = if (innerException != null) innerException!!.getCode() else ""

    override val message: String
        get() = if (innerException != null) innerException!!.getMessage() else ""

    val userFriendlyMessage: String?
        get() = if (innerException != null) innerException!!.getUserFriendlyMessage() else ""

    override fun toString(): String {
        return "BaseException{httpCode='$httpCode', innerException=$innerException}"
    }
}*/
