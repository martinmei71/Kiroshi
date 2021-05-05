package com.wappiter.data.appsession

import com.wappiter.data.ApiRetrofit
import com.wappiter.data.appsession.api.param.AppSessionParam
import com.wappiter.data.appsession.api.param.AppSessionParam.App
import com.wappiter.data.appsession.api.result.AppSessionApiModel
import com.wappiter.data.base.BaseNetworkDatasourceParamsImp
import com.wappiter.domain.appsession.AppSession
import com.wappiter.domain.appsession.data.AppSessionDatasource
import com.wappiter.domain.appsession.data.AppSessionDatasourceParams
import com.wappiter.domain.base.Mapper
import com.wappiter.domain.base.globalexception.ApiException
import com.wappiter.domain.base.globalexception.MapperException
import com.wappiter.domain.base.globalexception.NetworkException
import retrofit2.Call

/**
 * Created by porta on 18/12/18.
 */
class AppSessionDatasourceImp(private val mRetrofit: ApiRetrofit,
                              mapper: Mapper<AppSessionApiModel, AppSession>) : BaseNetworkDatasourceParamsImp<AppSessionDatasourceParams, AppSessionApiModel, AppSession>(mapper), AppSessionDatasource {
    @Throws(NetworkException::class, ApiException::class, MapperException::class)
    override fun createAppSession(values: AppSessionDatasourceParams): AppSession {
        return execute(values)
    }

    override fun run(datasourceParams: AppSessionDatasourceParams): Call<AppSessionApiModel> {
        val app = App(datasourceParams.appVersionCode, datasourceParams.appVersionName)
        val device = AppSessionParam.Device(datasourceParams.deviceUniqueIdentifier,
                datasourceParams.deviceManufacturerName,
                datasourceParams.deviceModelName)
        val os = AppSessionParam.OperatingSystem(datasourceParams.mobileOperatingSystemName,
                datasourceParams.mobileOperatingSystemVersionName)
        val params = AppSessionParam(datasourceParams.networkType, null, null, os, device, app)
        return mRetrofit.createSession(params)
    }
}