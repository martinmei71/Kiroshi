package com.wappiter.domain.preferences.service

import com.wappiter.domain.base.NameValue
import com.wappiter.infrastructure.datasource.AppPreferencesDatasource

/**
 * Created by porta on 08/12/2018.
 */
class PreferencesServiceImpl(private val mDatasource: AppPreferencesDatasource) : PreferencesService {
    override fun getString(key: String): String {
        return mDatasource.getString(key)
    }

    override fun getInteger(key: String): Int? {
        return mDatasource.getInteger(key)
    }

    override fun savePreferences(preferences: List<NameValue>) {
        mDatasource.savePreferences(preferences)
    }

}