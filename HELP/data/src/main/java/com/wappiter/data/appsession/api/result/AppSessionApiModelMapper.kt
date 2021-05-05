package com.wappiter.data.appsession.api.result

import com.wappiter.domain.appsession.AppSession
import com.wappiter.domain.base.Mapper
import java.util.*

/**
 * Created by porta on 18/12/18.
 */
class AppSessionApiModelMapper : Mapper<AppSessionApiModel, AppSession> {

    override fun map(model: AppSessionApiModel): AppSession {
        return AppSession(model.code, ArrayList())
    }
}