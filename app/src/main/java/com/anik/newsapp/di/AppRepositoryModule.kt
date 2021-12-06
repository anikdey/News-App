package com.anik.newsapp.di

import com.anik.newsapp.repository.PagingRepository
import com.anik.newsapp.repository.PagingRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class  AppRepositoryModule {

    @Binds
    @Singleton
    abstract fun provideManufacturerRepository(repository: PagingRepositoryImpl): PagingRepository

}