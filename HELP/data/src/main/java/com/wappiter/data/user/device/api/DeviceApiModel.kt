package com.wappiter.data.user.device.api

import com.google.gson.annotations.SerializedName
import com.wappiter.data.base.ApiModel

data class DeviceApiModel(

        @field:SerializedName("success")
        val success: Boolean,

        @field:SerializedName("errors")
        val errors: String

) : ApiModel