package com.wappiter.data.appsession.api.param

import com.google.gson.annotations.SerializedName

class AppSessionParam(@field:SerializedName("network_type") val networkType: String,
                      @field:SerializedName("longitude") val longitude: Float?,
                      @field:SerializedName("latitude") val latitude: Float?,
                      @field:SerializedName("operating_system") val operatingSystem: OperatingSystem,
                      @field:SerializedName("device") val device: Device,
                      @field:SerializedName("app") val app: App) {

    class OperatingSystem(@field:SerializedName("name") val name: String,
                          @field:SerializedName("version") val version: String)

    class Device(@field:SerializedName("uuid") val uuid: String,
                 @field:SerializedName("manufacturer") val manufacturer: String,
                 @field:SerializedName("model") val model: String)

    class App(@field:SerializedName("code") val code: String,
              @field:SerializedName("version") val version: String)

}