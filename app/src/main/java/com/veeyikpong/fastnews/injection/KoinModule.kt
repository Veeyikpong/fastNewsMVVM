package com.veeyikpong.fastnews.injection

import androidx.lifecycle.ViewModelProvider
import com.veeyikpong.fastnews.api.ApiService
import com.veeyikpong.fastnews.api.NewsRepository
import org.koin.dsl.module

private val apiServiceProvider = ApiService.create()
private val newsRepository = NewsRepository()

val netModule = module {
    single{ apiServiceProvider}
}

val newsRepo = module{
    single{newsRepository}
}