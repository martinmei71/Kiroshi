package com.wappiter.domain.user.resetpassword.data

import com.wappiter.domain.base.datasource.DatasourceParams

class ResetPasswordDatasourceParams(val email: String, val password: String, val code: String) : DatasourceParams
