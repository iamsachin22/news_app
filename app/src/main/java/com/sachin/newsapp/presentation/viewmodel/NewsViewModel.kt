package com.sachin.newsapp.presentation.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.*
import com.sachin.newsapp.data.model.APIResponse
import com.sachin.newsapp.data.model.Article
import com.sachin.newsapp.data.util.Resource
import com.sachin.newsapp.domain.usecase.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.lang.Error
import java.lang.Exception

class NewsViewModel(
    private val app:Application,
    private val getNewsHeadline: GetNewsHeadline,
    private val getSearchedNews: GetSearchedNews,
    private val saveNews: SaveNews,
    private val getSavedNews: GetSavedNews,
    private val deleteSavedNews: DeleteSavedNews
) : AndroidViewModel(app) {
    val newsHeadLines: MutableLiveData<Resource<APIResponse>> = MutableLiveData()

    fun getNewsHeadLines(country: String, page: Int) = viewModelScope.launch(Dispatchers.IO) {
        newsHeadLines.postValue(Resource.Loading())
        try{
            if(isNetworkAvailable(app)) {

                val apiResult = getNewsHeadline.execute(country, page)
                newsHeadLines.postValue(apiResult)
            }else{
                newsHeadLines.postValue(Resource.Error("Internet is not available"))
            }

        }catch (e: Exception){
            newsHeadLines.postValue(Resource.Error(e.message.toString()))
        }

    }

    private fun isNetworkAvailable(context: Context?):Boolean{
        if (context == null) return false
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        return true
                    }
                }
            }
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                return true
            }
        }
        return false

    }

    //searched function
    val searchedNews:MutableLiveData<Resource<APIResponse>> = MutableLiveData()

    fun searchNews(country: String,searchQuery:String,page: Int) = viewModelScope.launch {
        searchedNews.postValue(Resource.Loading())
        if(isNetworkAvailable(app)){
            val response = getSearchedNews.execute(
                country,searchQuery,page,
            )
            searchedNews.postValue(response)
        }
        else{
            searchedNews.postValue(Resource.Error("No Internet Connection"))
        }
    }

    //save articles to local DB
    fun saveArticles(article: Article) = viewModelScope.launch {
            saveNews.execute(article)
    }

    //get articles from DB
    fun getSavedNews() = liveData {
        getSavedNews.execute().collect {
            emit(it)
        }
    }

    // delete data
    fun deleteArticle(article: Article) = viewModelScope.launch {
        deleteSavedNews.execute(article)
    }
}
