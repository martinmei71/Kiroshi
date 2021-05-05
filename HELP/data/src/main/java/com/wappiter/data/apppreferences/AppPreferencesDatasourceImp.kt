package com.wappiter.data.apppreferences

import com.wappiter.domain.base.NameValue
import com.wappiter.infrastructure.datasource.AppPreferencesDatasource

/**
 * Created by porta on 7/11/17.
 */
class AppPreferencesDatasourceImp(private val mPreferencesHelper: PreferencesHelper) : AppPreferencesDatasource {
    override fun saveAppSessionCode(appSessionCode: String) {
        mPreferencesHelper.savePref(PreferencesNames.APP_SESSION_CODE_KEY, appSessionCode)
    }

    override fun cleanAppSession() {
        mPreferencesHelper.delete(PreferencesNames.APP_SESSION_CODE_KEY)
    }

    override fun saveBoolean(key: String,
                             value: Boolean) {
        mPreferencesHelper.savePref(key, value)
    }

    override fun getString(key: String): String {
        return mPreferencesHelper.getPref(key) ?: ""
    }

    override fun getBoolean(key: String): Boolean {
        return mPreferencesHelper.getPref(key, false)
    }

    override fun getInteger(key: String): Int? {
        return if (mPreferencesHelper.contains(key)) {
            mPreferencesHelper.getPref(key)
        } else -1
    }

    override val uniqueId: String?
        get() = null

    override fun savePreferences(preferences: List<NameValue>) {
        for (preference in preferences) {
            mPreferencesHelper.savePref(preference.name, preference.value ?: "")
        }
    }
}