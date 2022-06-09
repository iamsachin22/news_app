package com.sachin.newsapp.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sachin.newsapp.domain.usecase.GetNewsHeadline
import com.sachin.newsapp.domain.usecase.GetSearchedNews

class NewsViewModelFactory (
    private val app:Application,
    private val getNewsHeadline: GetNewsHeadline,
    private val getSearchedNews: GetSearchedNews
        ):ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NewsViewModel(
            app,
            getNewsHeadline,
            getSearchedNews
        ) as T
    }
}










