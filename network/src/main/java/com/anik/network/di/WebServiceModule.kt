package com.anik.network.di

import android.content.Context
import com.anik.network.BuildConfig
import com.anik.network.api.ApiService
import com.anik.network.util.Constants
import com.anik.network.util.Constants.Companion.CACHE_SIZE
import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.internal.platform.Platform
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object WebServiceModule {

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder().build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(@ApplicationContext applicationContext: Context): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(Constants.TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(Constants.TIME_OUT, TimeUnit.SECONDS)
            .writeTimeout(Constants.TIME_OUT, TimeUnit.SECONDS)
            .callTimeout(Constants.TIME_OUT, TimeUnit.MINUTES)
            .addInterceptor(getLogInterceptor(BuildConfig.DEBUG))
            .cache(getCache(applicationContext)).build()
    }

    private fun getLogInterceptor(isDebuggingEnabled: Boolean = false): Interceptor {
        val builder = LoggingInterceptor.Builder()
            .setLevel(if (isDebuggingEnabled) Level.BASIC else Level.NONE)
            .log(Platform.INFO)
            .tag("NEWS_APP")
            .request("Request")
            .response("Response")
            .addQueryParam("apiKey", Constants.API_KEY)
            //.addHeader("token", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9")
        builder.isDebugAble = isDebuggingEnabled
        return builder.build()
    }

    private fun getCache(context: Context) = Cache(context.cacheDir, CACHE_SIZE)

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

}
