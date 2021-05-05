package com.wappiter.data.user.device.api

import com.google.gson.annotations.SerializedName
import com.wappiter.data.base.ApiModel

data class DeviceRequestParams(

        @field:SerializedName("token")
        val token: String,

        @field:SerializedName("os")
        val os: String,

        @field:SerializedName("version")
        val version: String,

        @field:SerializedName("device_uuid")
        val deviceUuid: String

) : ApiModel