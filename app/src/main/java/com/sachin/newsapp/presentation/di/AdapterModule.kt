package com.sachin.newsapp.presentation.di

import com.sachin.newsapp.presentation.adapter.NewsAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class AdapterModule {
    @Singleton
    @Provides
    fun provideNewsAdapter():NewsAdapter{
        return NewsAdapter()
    }
}