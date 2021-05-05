package com.wappiter.domain.user.confirmaccount.data

import com.wappiter.domain.base.datasource.DatasourceParams

class ConfirmAccountDatasourceParams(val email: String, val code: String) : DatasourceParams