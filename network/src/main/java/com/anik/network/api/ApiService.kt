package com.anik.network.api

import com.anik.network.response.NewsResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("v2/top-headlines")
    suspend fun getBreakingNews(@Query("country") countryCode: String = "us", @Query("page") pageNumber: Int = 1): Response<NewsResponse>

    @GET("v2/everything")
    fun searchNews(@Query("q") searchQuery: String, @Query("page") pageNumber: Int = 1): Single<NewsResponse>

}