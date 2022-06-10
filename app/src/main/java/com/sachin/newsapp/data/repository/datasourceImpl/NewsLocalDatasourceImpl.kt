package com.sachin.newsapp.data.repository.datasourceImpl

import com.sachin.newsapp.data.db.ArticleDAO
import com.sachin.newsapp.data.model.Article
import com.sachin.newsapp.data.repository.datasource.NewsLocalDataSource
import kotlinx.coroutines.flow.Flow

class NewsLocalDatasourceImpl(
    private val articleDAO: ArticleDAO
):NewsLocalDataSource {
    override suspend fun saveArticlesToDB(article: Article) {
        articleDAO.insert(article)
    }

    override fun getSavedArticles(): Flow<List<Article>> {
        return articleDAO.getAllArticles()
    }

    override suspend fun deleteArticles(article: Article) {
        articleDAO.deleteArticles(article)
    }
}