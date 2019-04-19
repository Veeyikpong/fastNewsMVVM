package com.veeyikpong.fastnews.ui.searchnews.search

import com.veeyikpong.fastnews.ui.base.BaseView
import com.veeyikpong.fastnews.api.response.News

interface SearchContract{
    interface Presenter{
        fun init()
        fun searchNews(query: String)
        fun onDestroy()
    }

    interface View: BaseView<Presenter> {
        fun showLoading()
        fun hideLoading()
        fun displayTotalResult(totalResult: String)
        fun showNews(newsList: List<News>)
        fun showError(errorMessage: String="")
    }
}