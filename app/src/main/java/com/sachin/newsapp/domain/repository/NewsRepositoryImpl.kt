package com.sachin.newsapp.domain.repository

import com.sachin.newsapp.data.model.APIResponse
import com.sachin.newsapp.data.model.Article
import com.sachin.newsapp.data.repository.datasource.NewsLocalDataSource
import com.sachin.newsapp.data.repository.datasource.NewsRemoteDataSource
import com.sachin.newsapp.data.util.Resource
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class NewsRepositoryImpl(
    private val newsRemoteDataSource: NewsRemoteDataSource,
    private val newsLocalDataSource: NewsLocalDataSource
):NewsRepository {
    override suspend fun getNewsHeadlines(country:String,page:Int): Resource<APIResponse> {
      return responseToResource(newsRemoteDataSource.getTopHeadlines(country,page))
    }

    override suspend fun getSearchedNews(
        country: String,
        searchQuery: String,
        page: Int
    ): Resource<APIResponse> {
        return responseToResource(
            newsRemoteDataSource.getSearchedNews(country, searchQuery, page)
        )    }


    private fun responseToResource(response: Response<APIResponse>):Resource<APIResponse>{
        if(response.isSuccessful){
            response.body().let { result ->
               return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }

    override suspend fun saveNews(article: Article) {
        newsLocalDataSource.saveArticlesToDB(article)
    }

    override suspend fun deleteNews(article: Article) {
        newsLocalDataSource.deleteArticles(article)
    }

    override fun getSavedNews(): Flow<List<Article>> {
        return newsLocalDataSource.getSavedArticles()
    }
}