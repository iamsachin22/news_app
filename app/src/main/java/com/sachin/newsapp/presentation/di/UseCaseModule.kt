package com.sachin.newsapp.presentation.di
import com.sachin.newsapp.domain.repository.NewsRepository
import com.sachin.newsapp.domain.usecase.GetNewsHeadline
import com.sachin.newsapp.domain.usecase.GetSearchedNews
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class UseCaseModule {
   @Singleton
   @Provides
   fun provideGetNewsheadLinesUseCase(
       newsRepository: NewsRepository
   ):GetNewsHeadline{
      return GetNewsHeadline(newsRepository)
   }

   @Singleton
   @Provides
   fun provideGetSearchedNewsUseCase(
      newsRepository: NewsRepository
   ):GetSearchedNews{
      return GetSearchedNews(newsRepository)
   }
}


















