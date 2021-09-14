package com.anik.network.response

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Source(
    val id: String? = null,
    val name: String? = null
): Parcelable