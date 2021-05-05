package com.wappiter.domain.user.resetpassword.interactor

import com.wappiter.domain.base.datasource.DatasourceParams

class ResetPasswordInteractorValues(val password: String, val code: String) : DatasourceParams