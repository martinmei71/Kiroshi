package com.wappiter.infrastructure.accesor

/**
 * Created by porta on 6/07/18.
 */
interface ResourcesAccessor {
    fun getColor(color: Int): Int
    fun getString(string: Int): String
    fun getString(string: Int,
                  text: String): String

    fun getString(string: Int,
                  text: String,
                  text2: String): String

    fun getString(string: Int,
                  text: String,
                  text2: Int): String

    fun getString(stringKey: String): String
    fun getString(stringKey: String,
                  text: String): String
}