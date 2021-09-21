package com.anik.network.repositories.news

import androidx.lifecycle.LiveData
import com.anik.network.response.Article
import com.anik.network.response.NewsResponse
import com.anik.network.util.Resource

interface INewsRepository {

    suspend fun getBreakingNews(countryCode: String, pageNumber: Int): Resource<out NewsResponse>

    suspend fun searchNews(searchQuery: String, pageNumber: Int): Resource<out NewsResponse>

    suspend fun insert(article: Article): Long

    fun getArticles(): LiveData<List<Article>>

    suspend fun deleteArticle(article: Article)

}