package com.wappiter.data.base.mapper

import com.wappiter.domain.base.Mapper
import com.wappiter.domain.base.NameValue

/**
 * Created by porta on 09/02/2018.
 */
class ApiNameValueMapper : Mapper<ApiNameValue, NameValue> {

    override fun map(model: ApiNameValue): NameValue {
        return NameValue(model.name, model.value)
    }
}