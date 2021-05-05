package com.wappiter.data.apppreferences

import android.content.Context
import android.content.SharedPreferences

class PreferencesHelper(context: Context) {
    private val mSharedPreferences: SharedPreferences
    private val mEditor: SharedPreferences.Editor
    fun delete(key: String) {
        if (mSharedPreferences.contains(key)) {
            mEditor.remove(key).commit()
        }
    }

    fun savePref(key: String,
                 value: Any) {
        savePrefValue(key, value)
    }

    fun <T> getPref(key: String): T? {
        return getPref(key, null)
    }

    @Suppress("UNCHECKED_CAST")
    fun <T> getPref(key: String,
                    defValue: T): T {
        val returnValue = mSharedPreferences.all[key] as T?
        return returnValue ?: defValue
    }

    operator fun contains(key: String?): Boolean {
        return mSharedPreferences.contains(key)
    }

    @Suppress("UNCHECKED_CAST")
    private fun savePrefValue(key: String,
                              value: Any?) {
        if (value is Boolean) {
            mEditor.putBoolean(key, (value as Boolean?)!!)
        } else if (value is Int) {
            mEditor.putInt(key, (value as Int?)!!)
        } else if (value is Float) {
            mEditor.putFloat(key, (value as Float?)!!)
        } else if (value is Long) {
            mEditor.putLong(key, (value as Long?)!!)
        } else if (value is String) {
            mEditor.putString(key, value as String?)
        } else if (value is Enum<*>) {
            mEditor.putString(key, value.toString())
        } else if (value is Set<*>) {
            mEditor.putStringSet(key, value as Set<String?>?)
        } else if (value != null) {
            throw RuntimeException("Attempting to save non-primitive preference")
        }
        mEditor.apply()
    }

    init {
        val preferencesFile = "auth_preferences"
        mSharedPreferences = context.getSharedPreferences(preferencesFile, Context.MODE_PRIVATE)
        mEditor = mSharedPreferences.edit()
    }
}