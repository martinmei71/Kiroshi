package com.wappiter.domain.appsession.interactor

import com.wappiter.domain.base.interactor.InteractorValues

class AppSessionInteractorValues(val appVersionCode: String,
                                 val appVersionName: String,
                                 val networkType: String,
                                 val deviceUniqueIdentifier: String,
                                 val mobileOperatingSystemName: String,
                                 val mobileOperatingSystemVersionName: String,
                                 val deviceManufacturerName: String,
                                 val deviceModelName: String) : InteractorValues()

