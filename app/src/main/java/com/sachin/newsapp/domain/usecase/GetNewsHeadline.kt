package com.sachin.newsapp.domain.usecase

import com.sachin.newsapp.data.model.APIResponse
import com.sachin.newsapp.data.util.Resource
import com.sachin.newsapp.domain.repository.NewsRepository

class GetNewsHeadline(private val newsRepository: NewsRepository) {
    suspend fun execute(country:String,page:Int):Resource<APIResponse>{
        return newsRepository.getNewsHeadlines(country,page)
    }
}