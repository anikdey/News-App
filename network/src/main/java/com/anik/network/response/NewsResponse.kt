package com.anik.network.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NewsResponse(
    val articles: MutableList<Article>,
    val status: String,
    val totalResults: Int
)