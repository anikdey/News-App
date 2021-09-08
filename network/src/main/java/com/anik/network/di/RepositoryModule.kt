package com.anik.network.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class  RepositoryModule {

//    @Binds
//    @Singleton
//    abstract fun provideRepository(api: GifRepositoryImpl): GifRepository

}