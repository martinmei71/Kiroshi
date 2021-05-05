package com.wappiter.infrastructure.device

interface DeviceAccessor {

    fun getOSName(): String

    fun getOSVersion(): String

    fun getDeviceUUID(): String

    fun getDeviceManufacturer(): String

    fun getDeviceModel(): String

    fun getAppCode(): String

    fun getAppVersion(): String

    fun getNetworkType(): String
}