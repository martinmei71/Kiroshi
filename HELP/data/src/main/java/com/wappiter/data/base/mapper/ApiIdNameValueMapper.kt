package com.wappiter.data.base.mapper

import com.wappiter.domain.base.IdNameValue
import com.wappiter.domain.base.Mapper

/**
 * Created by porta on 09/02/2018.
 */
class ApiIdNameValueMapper : Mapper<ApiIdNameValue, IdNameValue> {

    override fun map(model: ApiIdNameValue): IdNameValue {
        return IdNameValue(model.id, model.name, model.value)
    }
}