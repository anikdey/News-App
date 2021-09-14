package com.anik.network.response

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.anik.network.util.Constants
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Entity(tableName = Constants.tableArticle)
data class Article(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    val author: String? = null,
    val content: String? = null,
    val description: String? = null,
    val publishedAt: String? = null,
    val source: Source? = null,
    val title: String? = null,
    val url: String? = null,
    val urlToImage: String? = null
)