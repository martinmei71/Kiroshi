package com.wappiter.data.user.device.param

import com.google.gson.annotations.SerializedName

//TODO
class DeviceParam(@field:SerializedName("device") private val device: Device,
                  @field:SerializedName("operating_system") private val operatingSystem: OperatingSystem) {

    class OperatingSystem(@field:SerializedName("name") val name: String,
                          @field:SerializedName("version") val version: String)

    class Device(@field:SerializedName("uuid") val uuid: String)

}