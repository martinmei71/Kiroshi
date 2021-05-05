package com.wappiter.domain.appsession.data

import com.wappiter.domain.base.datasource.DatasourceParams

/**
 * Created by porta on 18/12/18.
 */
class AppSessionDatasourceParams(val appVersionCode: String,
                                 val appVersionName: String,
                                 val networkType: String,
                                 val deviceUniqueIdentifier: String,
                                 val mobileOperatingSystemName: String,
                                 val mobileOperatingSystemVersionName: String,
                                 val deviceManufacturerName: String,
                                 val deviceModelName: String) : DatasourceParams