package com.wappiter.domain.user.registration.data

import com.wappiter.domain.base.datasource.DatasourceParams

class RegistrationDatasourceParams(var firstName: String,
                                   var lastName: String,
                                   var email: String,
                                   var photoUrl: String?,
                                   var userId: String?,
                                   var tokenId: String?,
                                   var providerName: String,
                                   val password: String?) : DatasourceParams
