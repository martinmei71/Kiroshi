package com.wappiter.domain.device.data

import com.wappiter.domain.base.datasource.DatasourceParams


class SendDeviceDatasourceParams(val token: String,
                                 val os: String,
                                 val version: String,
                                 val deviceUuid: String) : DatasourceParams
