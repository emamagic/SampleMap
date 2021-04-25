package com.geo.sampleapplication.network.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Latlng(
        var lat: Double,
        var lon: Double
): Parcelable