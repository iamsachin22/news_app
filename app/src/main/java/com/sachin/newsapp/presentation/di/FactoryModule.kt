package com.sachin.newsapp.presentation.di

import android.app.Application
import com.sachin.newsapp.domain.usecase.GetNewsHeadline
import com.sachin.newsapp.domain.usecase.GetSearchedNews
import com.sachin.newsapp.presentation.viewmodel.NewsViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class FactoryModule {
    @Singleton
    @Provides
    fun provideNewsViewModelFactory(
        application: Application,
        getNewsHeadline: GetNewsHeadline,
        getSearchedNews: GetSearchedNews
    ):NewsViewModelFactory{
        return NewsViewModelFactory(
            application,
            getNewsHeadline,
            getSearchedNews

        )
    }
}