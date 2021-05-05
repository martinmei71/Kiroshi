package com.wappiter.data.user

import com.google.gson.annotations.SerializedName
import com.wappiter.data.base.ApiModel

class UserApiModel(@SerializedName("email") val email: String,
                   @SerializedName("api_key") val apiKey: String,
                   @SerializedName("locale") val locale: String,
                   @SerializedName("has_favourites") val hasFavourites: Boolean,
                   @SerializedName("has_missing_data") val hasMissingData: Boolean) : ApiModel