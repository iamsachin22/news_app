package com.sachin.newsapp.domain.usecase

import com.sachin.newsapp.data.model.Article
import com.sachin.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetSavedNews(private val newsRepository: NewsRepository) {
    fun execute(): Flow<List<Article>>{
        return newsRepository.getSavedNews()
    }
}