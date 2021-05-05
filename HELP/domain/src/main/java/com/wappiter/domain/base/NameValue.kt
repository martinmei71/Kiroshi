package com.wappiter.domain.base

import org.parceler.Parcel
import org.parceler.ParcelConstructor

/**
 * Created by porta on 09/02/2018.
 */
@Parcel(Parcel.Serialization.BEAN)
class NameValue @ParcelConstructor constructor(val name: String,
                                               var value: String?) {

    override fun toString(): String {
        return name + if (value != null && "" != value) ": $value" else ""
    }

}