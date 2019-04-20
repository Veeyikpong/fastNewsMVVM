package com.veeyikpong.fastnews.ui.searchnews.search

import android.app.Application
import android.view.View
import androidx.lifecycle.*
import com.veeyikpong.fastnews.BuildConfig
import com.veeyikpong.fastnews.api.ApiService
import com.veeyikpong.fastnews.api.NewsRepository
import com.veeyikpong.fastnews.api.Resource
import com.veeyikpong.fastnews.api.response.News
import com.veeyikpong.fastnews.api.response.SearchNewsResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import org.koin.core.KoinComponent
import org.koin.core.inject

class SearchViewModel : ViewModel(), KoinComponent{
    private val newsRepo : NewsRepository by inject()
    private val apiService: ApiService by inject()
    private var searchNewsResponseLiveData = MutableLiveData<Resource<SearchNewsResponse>>()
    private var selectedNews: MutableLiveData<News> = MutableLiveData()

    fun searchNews(query: String) {
        newsRepo.getNews(searchNewsResponseLiveData,query)
    }

    fun selectNews(news: News){
        selectedNews.value = news
    }

    fun getNewsResponse() : LiveData<Resource<SearchNewsResponse>>{
        return searchNewsResponseLiveData
    }

    fun getSelectedNews(): MutableLiveData<News>{
        return selectedNews
    }
}