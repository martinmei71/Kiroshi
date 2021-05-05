package com.wappiter.app.infrastucture.resources

import android.content.Context
import androidx.core.content.ContextCompat
import com.wappiter.infrastructure.accesor.ResourcesAccessor

/**
 * Created by porta on 6/07/18.
 */
class ResourcesAccessorImpl(val context: Context) : ResourcesAccessor {

    override fun getColor(color: Int): Int {
        return ContextCompat.getColor(context, color)
    }

    override fun getString(string: Int): String {
        return context.getString(string)
    }

    override fun getString(string: Int,
                           text: String): String {
        return context.getString(string, text)
    }

    override fun getString(string: Int,
                           text: String,
                           text2: String): String {
        return context.getString(string, text, text2)
    }

    override fun getString(string: Int,
                           text: String,
                           text2: Int): String {
        return context.getString(string, text, text2)
    }

    override fun getString(stringKey: String): String {
        return context
                .getString(context.resources.getIdentifier(stringKey, "string", context.packageName))
    }

    override fun getString(stringKey: String,
                           text: String): String {
        return context
                .getString(context.resources.getIdentifier(stringKey, "string", context.packageName), text)
    }
}