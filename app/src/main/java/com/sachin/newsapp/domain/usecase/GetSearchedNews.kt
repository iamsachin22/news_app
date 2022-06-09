package com.sachin.newsapp.domain.usecase

import com.sachin.newsapp.data.model.APIResponse
import com.sachin.newsapp.data.util.Resource
import com.sachin.newsapp.domain.repository.NewsRepository

class GetSearchedNews(private val newsRepository: NewsRepository) {
    suspend fun execute(searchQuery:String):Resource<APIResponse>{
        return newsRepository.getSearchedNews(searchQuery)
    }
}