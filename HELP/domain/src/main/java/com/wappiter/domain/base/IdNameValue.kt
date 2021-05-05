package com.wappiter.domain.base

import org.parceler.Parcel
import org.parceler.ParcelConstructor

/**
 * Created by porta on 09/02/2018.
 */
@Parcel(Parcel.Serialization.BEAN)
class IdNameValue(val id: Int,
                  val name: String) {
    var value: String? = null

    @ParcelConstructor
    constructor(id: Int,
                name: String,
                value: String?) : this(id, name) {
        this.value = value
    }

    override fun toString(): String {
        return name + if (value != null && "" != value) ": $value" else ""
    }

}