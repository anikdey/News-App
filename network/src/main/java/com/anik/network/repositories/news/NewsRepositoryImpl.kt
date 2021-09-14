package com.anik.network.repositories.news

import com.anik.network.api.ApiService
import com.anik.network.dao.ArticleDao
import com.anik.network.response.NewsResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val articleDao: ArticleDao
) : INewsRepository {

    override suspend fun getBreakingNews(countryCode: String, pageNumber: Int): Response<NewsResponse> {
        return apiService.getBreakingNews(countryCode, pageNumber)
    }

    override fun searchNews(searchQuery: String, pageNumber: Int): Single<NewsResponse> {
        return apiService.searchNews(searchQuery, pageNumber)
    }

}