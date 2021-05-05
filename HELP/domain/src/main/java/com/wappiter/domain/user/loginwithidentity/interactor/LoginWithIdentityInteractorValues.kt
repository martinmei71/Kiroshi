package com.wappiter.domain.user.loginwithidentity.interactor

import com.wappiter.domain.base.datasource.DatasourceParams

class LoginWithIdentityInteractorValues(var firstName: String,
                                        var lastName: String,
                                        var email: String,
                                        var photoUrl: String?,
                                        var userId: String,
                                        var idToken: String,
                                        var provider: String) : DatasourceParams {

}
