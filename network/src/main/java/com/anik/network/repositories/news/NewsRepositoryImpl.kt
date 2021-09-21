package com.anik.network.repositories.news

import androidx.lifecycle.LiveData
import com.anik.network.api.ApiService
import com.anik.network.dao.ArticleDao
import com.anik.network.di.qualifiers.IoDispatcher
import com.anik.network.repositories.base.BaseRepository
import com.anik.network.response.Article
import com.anik.network.response.NewsResponse
import com.anik.network.util.Resource
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val articleDao: ArticleDao,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : INewsRepository, BaseRepository() {

    override suspend fun getBreakingNews(countryCode: String, pageNumber: Int): Resource<out NewsResponse> {
        return safeApiCall(ioDispatcher){ apiService.getBreakingNews("us", 2) }
    }

    override suspend fun searchNews(searchQuery: String, pageNumber: Int): Resource<out NewsResponse> {
        return safeApiCall(ioDispatcher){ apiService.searchNews(searchQuery, pageNumber) }
    }

    override suspend fun insert(article: Article): Long {
        return articleDao.insert(article)
    }

    override fun getArticles(): LiveData<List<Article>> {
        return articleDao.getArticles()
    }

    override suspend fun deleteArticle(article: Article) {
        articleDao.deleteArticle(article)
    }

}