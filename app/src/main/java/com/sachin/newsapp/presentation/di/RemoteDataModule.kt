package com.sachin.newsapp.presentation.di


import com.sachin.newsapp.data.api.NewsAPIService
import com.sachin.newsapp.data.repository.datasource.NewsRemoteDataSource
import com.sachin.newsapp.data.repository.datasourceImpl.NewsRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RemoteDataModule {

    @Singleton
    @Provides
    fun provideNewsRemoteDataSource(
        newsAPIService: NewsAPIService
    ): NewsRemoteDataSource {
       return NewsRemoteDataSourceImpl(newsAPIService)
    }

}












