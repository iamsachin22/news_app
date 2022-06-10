package com.sachin.newsapp.data.db

import androidx.room.*
import com.sachin.newsapp.data.model.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(article: Article)

    @Query("SELECT * from articles")
    fun getAllArticles(): Flow<List<Article>>
    @Delete
    suspend fun deleteArticles(article: Article)
}