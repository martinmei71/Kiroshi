package com.wappiter.infrastructure.exceptions

/**
 * Created by porta on 16/01/2018.
 */
object ErrorCodes {
    // HTML ERROR CODES
    const val HTML_UNAUTHORIZED = "401"
    const val HTML_FORBIDDEN = "403"
    const val HTML_NOT_FOUND = "404"

    // API ERROR CODES
    const val API_IDENTITY_NOT_FOUND = "103"


    class User {
        companion object {
            const val INVALID_EMAIL_OR_PASSWORD = "404"
            const val EMAIL_NOT_VALIDATED = "405"
        }
    }

    class Session {
        companion object {
            const val EXPIRED_APP_VERSION = "5002"
        }
    }
}