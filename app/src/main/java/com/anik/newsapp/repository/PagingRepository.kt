package com.anik.newsapp.repository

import androidx.paging.PagingData
import com.anik.network.response.Article
import kotlinx.coroutines.flow.Flow

interface PagingRepository {

    fun getBreakingNews(countryCode: String): Flow<PagingData<Article>>

    fun searchNews(query: String): Flow<PagingData<Article>>

}