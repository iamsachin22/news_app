package com.sachin.newsapp.data.repository.datasource

import com.sachin.newsapp.data.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsLocalDataSource {
    suspend fun saveArticlesToDB(article: Article)

    fun getSavedArticles():Flow<List<Article>>

    suspend fun deleteArticles(article: Article)
}