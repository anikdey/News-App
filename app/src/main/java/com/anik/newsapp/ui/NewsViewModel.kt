package com.anik.newsapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.anik.network.repositories.news.INewsRepository
import com.anik.network.response.Article
import com.anik.network.response.NewsResponse
import com.anik.network.util.Resource
import com.app.core.base.viewmodel.BaseViewModel
import com.app.core.base.viewmodel.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsRepository: INewsRepository
) : BaseViewModel() {

    var breakingNewsPage = 1
    val breakingNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()

    var searchNewsPage = 1
    val searchNewsLiveData by lazy { MutableLiveData<NewsResponse>() }

    init {
        getBreakingNews("us")
    }

    fun getBreakingNews(countryCode: String) = viewModelScope.launch {
        breakingNews.postValue(Resource.Loading())
        val response = newsRepository.getBreakingNews(countryCode, breakingNewsPage)
        breakingNews.postValue(handleBreakingNewsResponse(response))
    }

    private fun handleBreakingNewsResponse(response: Response<NewsResponse>): Resource<NewsResponse> {
        if(response.isSuccessful) {
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(response.message())
    }

    fun searchNews(query: String) {
        var disposable = newsRepository.searchNews(query, searchNewsPage)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { showLoader.postValue(true) }
            .doOnError { showLoader.postValue(false) }
            .doFinally { showLoader.postValue(false) }
            .subscribe({
                searchNewsLiveData.postValue(it)
            }, {
                toastMessage.postValue(it.message)
            })
        compositeDisposable.add(disposable)
    }

    private fun handleSearchNewsResponse(response: Response<NewsResponse>): Resource<NewsResponse> {
        if(response.isSuccessful) {
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(response.message())
    }


    fun saveArticle(article: Article) {

    }


}