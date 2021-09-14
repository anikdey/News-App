package com.anik.network.repositories.news

import com.anik.network.response.NewsResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.Response

interface INewsRepository {

    suspend fun getBreakingNews(countryCode: String, pageNumber: Int): Response<NewsResponse>

    fun searchNews(searchQuery: String, pageNumber: Int): Single<NewsResponse>

}