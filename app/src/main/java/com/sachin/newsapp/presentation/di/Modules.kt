//package com.sachin.newsapp.presentation.di
//
//import com.sachin.newsapp.domain.repository.NewsRepository
//import com.sachin.newsapp.domain.usecase.GetNewsHeadline
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.components.SingletonComponent
//import javax.inject.Singleton
//
//@Module
//@InstallIn(SingletonComponent::class)
//class Modules {
//    fun provideGetNewsHeadline(
//        newsRepository: NewsRepository
//    ):GetNewsHeadline{
//        return GetNewsHeadline(newsRepository)
//    }
//}