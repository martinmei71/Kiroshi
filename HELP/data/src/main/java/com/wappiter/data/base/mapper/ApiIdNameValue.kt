package com.wappiter.data.base.mapper

import com.google.gson.annotations.SerializedName

/**
 * Created by porta on 09/02/2018.
 */
class ApiIdNameValue {
    @SerializedName("id")
    val id = 0

    @SerializedName("name")
    val name: String = ""

    @SerializedName("value")
    var value: String? = null

}