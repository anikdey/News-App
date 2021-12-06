package com.anik.newsapp.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.anik.network.repositories.news.NewsRepository
import com.anik.network.response.Article
import com.anik.network.util.Resource

class SearchNewsPagingSource(
    private val newsRepository: NewsRepository,
    private val query: String
): PagingSource<Int, Article>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val position = params.key ?: 1
        try{
            return when(val response = newsRepository.searchNews(query, position)) {
                is Resource.Success -> {
                    LoadResult.Page(data = response.data.articles,
                        prevKey = if(position==1) null else position-1,
                        nextKey = if(response.data.articles.size==0) null else position+1)
                }
                is Resource.Error -> LoadResult.Error(response.exception)
                else -> { LoadResult.Error(NullPointerException()) }
            }

        } catch (throwable: Throwable) {
            throw throwable
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

}