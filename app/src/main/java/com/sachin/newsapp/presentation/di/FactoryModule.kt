package com.sachin.newsapp.presentation.di

import android.app.Application
import com.sachin.newsapp.domain.usecase.*
import com.sachin.newsapp.presentation.viewmodel.NewsViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FactoryModule {
    @Singleton
    @Provides
    fun provideNewsViewModelFactory(
        application: Application,
        getNewsHeadline: GetNewsHeadline,
        getSearchedNews: GetSearchedNews,
        saveNews: SaveNews,
        getSavedNews: GetSavedNews,
        deleteSavedNews: DeleteSavedNews
    ):NewsViewModelFactory{
        return NewsViewModelFactory(
            application,
            getNewsHeadline,
            getSearchedNews,
            saveNews,
            getSavedNews,
            deleteSavedNews
        )
    }
}