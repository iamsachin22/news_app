package com.sachin.newsapp.presentation.di
import com.sachin.newsapp.domain.repository.NewsRepository
import com.sachin.newsapp.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
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

   @Singleton
   @Provides
   fun provideSaveNewsUseCase(
      newsRepository: NewsRepository
   ):SaveNews{
      return SaveNews(newsRepository)
   }

   @Singleton
   @Provides
   fun getSaveNewsUseCase(
      newsRepository: NewsRepository
   ):GetSavedNews{
      return GetSavedNews(newsRepository)
   }

   @Singleton
   @Provides
   fun deleteSavedNewsUseCase(
      newsRepository: NewsRepository
   ):DeleteSavedNews{
      return DeleteSavedNews(newsRepository)
   }
}


















