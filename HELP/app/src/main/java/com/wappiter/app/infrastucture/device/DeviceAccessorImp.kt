package com.wappiter.app.infrastucture.device

import android.content.Context
import com.wappiter.app.util.NetworkUtils
import com.wappiter.app.util.SystemUtils
import com.wappiter.infrastructure.device.DeviceAccessor

class DeviceAccessorImp(val context: Context) : DeviceAccessor {

    override fun getOSName(): String {
        return SystemUtils.operatingSystem
    }

    override fun getOSVersion(): String {
        return SystemUtils.osVersion
    }

    override fun getDeviceUUID(): String {
        return SystemUtils.uniqueIdentifier
    }

    override fun getDeviceManufacturer(): String {
        return SystemUtils.deviceManufacturer
    }

    override fun getDeviceModel(): String {
        return SystemUtils.deviceModel
    }

    override fun getAppCode(): String {
        return SystemUtils.appCode
    }

    override fun getAppVersion(): String {
        return SystemUtils.appVersion
    }

    override fun getNetworkType(): String {
        return NetworkUtils.getNetworkTypeParam(context)
    }
}