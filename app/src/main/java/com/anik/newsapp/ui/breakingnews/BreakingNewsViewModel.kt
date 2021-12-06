package com.anik.newsapp.ui.breakingnews

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.anik.network.repositories.news.NewsRepository
import com.anik.network.response.Article
import com.anik.network.response.NewsResponse
import com.anik.newsapp.repository.PagingRepository
import com.app.core.base.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BreakingNewsViewModel @Inject constructor(
    private val pagingRepository: PagingRepository
) : BaseViewModel() {

    private val _breakingNewsLiveData = MutableLiveData<PagingData<Article>>()
    val breakingNewsLiveData: LiveData<PagingData<Article>> = _breakingNewsLiveData

    fun getBreakingNews(countryCode: String) = viewModelScope.launch {
        pagingRepository.getBreakingNews(countryCode).cachedIn(viewModelScope).collectLatest {
            _breakingNewsLiveData.postValue(it)
        }
    }

}