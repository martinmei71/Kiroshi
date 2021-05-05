package com.wappiter.domain.user

import com.wappiter.domain.base.Model
import org.parceler.Parcel
import org.parceler.ParcelConstructor

@Parcel(Parcel.Serialization.BEAN)
class User @ParcelConstructor constructor(val email: String,
                                          val locale: String,
                                          val hasFavourites: Boolean,
                                          val hasMissingData: Boolean) : Model {
}