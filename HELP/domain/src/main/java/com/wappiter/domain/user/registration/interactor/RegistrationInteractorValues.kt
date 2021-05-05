package com.wappiter.domain.user.registration.interactor

import com.wappiter.domain.base.datasource.DatasourceParams

class RegistrationInteractorValues(var firstName: String,
                                   var lastName: String,
                                   var email: String,
                                   var photoUrl: String?,
                                   var provider: String,
                                   var password: String) : DatasourceParams