package com.sachin.newsapp.data.api

import com.sachin.newsapp.BuildConfig
import com.sachin.newsapp.data.model.APIResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPIService {
    @GET("v2/top-headlines")
     suspend fun getTopHeadlines(
        @Query("country")
        country:String,
        @Query("page")
        page:Int,
        @Query("apikey")
        apikey:String =BuildConfig.API_KEY
    ): Response<APIResponse>
}