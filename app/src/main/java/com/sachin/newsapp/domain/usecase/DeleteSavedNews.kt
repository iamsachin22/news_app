package com.sachin.newsapp.domain.usecase

import com.sachin.newsapp.data.model.Article
import com.sachin.newsapp.domain.repository.NewsRepository

class DeleteSavedNews(private val newsRepository: NewsRepository) {
    suspend fun execute(article: Article)= newsRepository.deleteNews(article)
}