package com.wappiter.data.base.mapper

import com.wappiter.domain.base.Mapper
import com.wappiter.domain.base.VoidModel

class VoidApiModelMapper : Mapper<ApiVoid, VoidModel> {

    override fun map(model: ApiVoid): VoidModel {
        return VoidModel()
    }
}