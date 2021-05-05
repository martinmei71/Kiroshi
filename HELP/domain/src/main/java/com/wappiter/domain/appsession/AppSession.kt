package com.wappiter.domain.appsession

import com.wappiter.domain.base.IdNameValue
import com.wappiter.domain.base.Model

/**
 * Created by porta on 18/12/18.
 */
class AppSession(val code: String,
                 val parameterValues: List<IdNameValue>) : Model