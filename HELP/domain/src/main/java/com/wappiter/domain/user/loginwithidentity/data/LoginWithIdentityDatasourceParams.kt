package com.wappiter.domain.user.loginwithidentity.data

import com.wappiter.domain.base.datasource.DatasourceParams

class LoginWithIdentityDatasourceParams(var firstName: String,
                                        var lastName: String,
                                        var email: String,
                                        var photoUrl: String?,
                                        var userId: String,
                                        var tokenId: String,
                                        var providerName: String) : DatasourceParams
