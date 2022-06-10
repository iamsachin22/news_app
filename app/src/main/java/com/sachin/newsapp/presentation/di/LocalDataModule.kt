package com.sachin.newsapp.presentation.di

import com.sachin.newsapp.data.db.ArticleDAO
import com.sachin.newsapp.data.repository.datasource.NewsLocalDataSource
import com.sachin.newsapp.data.repository.datasourceImpl.NewsLocalDatasourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDataModule {
    @Singleton
    @Provides
    fun provideLocalDataSource(articleDAO: ArticleDAO):NewsLocalDataSource{
        return NewsLocalDatasourceImpl(articleDAO)
    }
}