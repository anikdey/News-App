package com.anik.newsapp.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.anik.network.repositories.news.NewsRepository
import com.anik.network.response.Article
import com.anik.newsapp.pagingsource.BreakingNewsPagingSource
import com.anik.newsapp.pagingsource.SearchNewsPagingSource
import com.app.core.util.Constants
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PagingRepositoryImpl @Inject constructor(
    private val newsRepository: NewsRepository
) : PagingRepository {

    override fun getBreakingNews(countryCode: String): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = Constants.pageSize, maxSize = Constants.maxSize, initialLoadSize = Constants.initialLoadSize, prefetchDistance = Constants.prefetchDistance, enablePlaceholders = false),
            pagingSourceFactory = { BreakingNewsPagingSource(newsRepository, countryCode) }
        ).flow
    }

    override fun searchNews(query: String): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = Constants.pageSize, maxSize = Constants.maxSize, initialLoadSize = Constants.initialLoadSize, prefetchDistance = Constants.prefetchDistance, enablePlaceholders = false),
            pagingSourceFactory = { SearchNewsPagingSource(newsRepository, query) }
        ).flow
    }
}