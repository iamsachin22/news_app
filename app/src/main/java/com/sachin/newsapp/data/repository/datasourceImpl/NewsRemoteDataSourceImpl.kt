package com.sachin.newsapp.data.repository.datasourceImpl

import com.sachin.newsapp.data.api.NewsAPIService
import com.sachin.newsapp.data.model.APIResponse
import com.sachin.newsapp.data.repository.datasource.NewsRemoteDataSource
import retrofit2.Response

class NewsRemoteDataSourceImpl(
    private val newsAPIService: NewsAPIService,
):NewsRemoteDataSource {
    override suspend fun getTopHeadlines(country:String,page:Int): Response<APIResponse> {
       return newsAPIService.getTopHeadlines(country, page)
    }

}