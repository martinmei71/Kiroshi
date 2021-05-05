package com.wappiter.domain.preferences.service

import com.wappiter.domain.base.NameValue
import com.wappiter.domain.base.Service
import com.wappiter.domain.base.globalexception.BaseException

/**
 * Created by porta on 08/12/2018.
 */
interface PreferencesService : Service {
    @Throws(BaseException::class)
    fun getString(key: String): String

    @Throws(BaseException::class)
    fun getInteger(key: String): Int?

    @Throws(BaseException::class)
    fun savePreferences(preferences: List<NameValue>)
}