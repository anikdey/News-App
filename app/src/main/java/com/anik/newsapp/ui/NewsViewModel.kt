package com.anik.newsapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.anik.network.repositories.news.NewsRepository
import com.anik.network.response.Article
import com.anik.network.response.NewsResponse
import com.anik.network.util.Resource
import com.app.core.base.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : BaseViewModel() {

    var breakingNewsPage = 1
    val breakingNewsLiveData: MutableLiveData<NewsResponse> = MutableLiveData()
    private var breakingNewsResponse: NewsResponse? = null

    var searchNewsPage = 1
    val searchNewsLiveData by lazy { MutableLiveData<NewsResponse>() }
    private var searchNewsResponse: NewsResponse? = null


    fun searchNews(query: String) = viewModelScope.launch {
        showLoader.value = true
        val response = newsRepository.searchNews(query, searchNewsPage)
        showLoader.value = false
        when(response) {
            is Resource.Success -> {
                response.data?.let { newsResponse ->
                    searchNewsPage++
                    if(searchNewsResponse == null) {
                        searchNewsResponse = newsResponse
                    } else {
                        val oldArticles = searchNewsResponse?.articles
                        val newArticles = newsResponse.articles
                        oldArticles?.addAll(newArticles)
                    }
                    searchNewsLiveData.value = searchNewsResponse
                }
            }
            else -> {
                //handleError(response)
            }
        }
    }

    fun getSavedNews() = newsRepository.getArticles()

    fun saveArticle(article: Article) {
        viewModelScope.launch {
            newsRepository.insert(article)
        }
    }

    fun deleteArticle(article: Article) {
        viewModelScope.launch {
            newsRepository.deleteArticle(article)
        }
    }

}