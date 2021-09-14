package com.anik.network.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Source(
    val id: Any? = null,
    val name: String? = null
)