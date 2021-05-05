package com.wappiter.data.user.device.api

import com.wappiter.data.ApiRetrofit
import com.wappiter.data.base.BaseNetworkDatasourceParamsImp
import com.wappiter.domain.base.VoidModel
import com.wappiter.domain.base.globalexception.ApiException
import com.wappiter.domain.base.globalexception.MapperException
import com.wappiter.domain.base.globalexception.NetworkException
import com.wappiter.domain.device.data.SendDeviceDatasource
import com.wappiter.domain.device.data.SendDeviceDatasourceParams
import retrofit2.Call

/**
 * Created by porta on 18/12/18.
 */

class SendDeviceDatasourceImp(private val retrofit: ApiRetrofit) :
        BaseNetworkDatasourceParamsImp<SendDeviceDatasourceParams, DeviceApiModel, VoidModel>(), SendDeviceDatasource {

    @Throws(NetworkException::class, ApiException::class, MapperException::class)
    override fun sendDevice(params: SendDeviceDatasourceParams) {
        execute(params)
    }

    override fun run(datasourceParams: SendDeviceDatasourceParams): Call<DeviceApiModel> {
        return retrofit.updateDevice(DeviceRequestParams(datasourceParams.token, datasourceParams.os, datasourceParams.version, datasourceParams.deviceUuid))
    }
}
