package com.amud.thevoid

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Location constructor(val name: String?, val lat: Double?, val long: Double?) : Parcelable