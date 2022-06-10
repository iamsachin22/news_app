package com.sachin.newsapp.presentation.di


import com.sachin.newsapp.data.repository.datasource.NewsLocalDataSource
import com.sachin.newsapp.domain.repository.NewsRepositoryImpl
import com.sachin.newsapp.data.repository.datasource.NewsRemoteDataSource
import com.sachin.newsapp.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideNewsRepository(
        newsRemoteDataSource: NewsRemoteDataSource,
        newsLocalDataSource: NewsLocalDataSource
    ): NewsRepository {
        return NewsRepositoryImpl(newsRemoteDataSource,newsLocalDataSource)
    }

}














