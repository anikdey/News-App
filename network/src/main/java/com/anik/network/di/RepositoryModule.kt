package com.anik.network.di

import com.anik.network.repositories.news.NewsRepository
import com.anik.network.repositories.news.NewsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class  RepositoryModule {

    @Binds
    @Singleton
    abstract fun provideRepository(repository: NewsRepositoryImpl): NewsRepository

}