package com.anik.network.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)