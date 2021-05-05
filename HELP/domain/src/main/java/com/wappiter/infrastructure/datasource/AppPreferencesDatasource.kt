package com.wappiter.infrastructure.datasource

import com.wappiter.domain.base.NameValue

/**
 * Created by porta on 7/11/17.
 */

interface AppPreferencesDatasource {

    val uniqueId: String?

    fun saveAppSessionCode(appSessionCode: String)

    fun cleanAppSession()

    fun saveBoolean(key: String, value: Boolean)

    fun savePreferences(preferences: List<NameValue>)

    fun getString(key: String): String

    fun getBoolean(key: String): Boolean

    fun getInteger(key: String): Int?
}