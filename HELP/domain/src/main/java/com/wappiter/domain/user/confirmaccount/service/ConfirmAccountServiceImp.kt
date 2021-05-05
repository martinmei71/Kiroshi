package com.wappiter.domain.user.confirmaccount.service

import com.wappiter.domain.user.UserSession
import com.wappiter.domain.user.confirmaccount.data.ConfirmAccountDatasource
import com.wappiter.domain.user.confirmaccount.data.ConfirmAccountDatasourceParams
import com.wappiter.domain.user.session.UserSessionLocalDatasource

class ConfirmAccountServiceImp(private val confirmAccountDatasource: ConfirmAccountDatasource,
                               val userSessionLocalDatasource: UserSessionLocalDatasource) : ConfirmAccountService {

    override fun confirm(code: String) {
        val userSession: UserSession = userSessionLocalDatasource.getUserSession()!!
        confirmAccountDatasource.confirm(ConfirmAccountDatasourceParams(userSession.user.email, code))
    }
}