package com.veeyikpong.threefragmentsexample.search

import com.veeyikpong.threefragmentsexample.BaseView
import com.veeyikpong.threefragmentsexample.api.response.News

interface SearchContract{
    interface Presenter{
        fun init()
        fun searchNews(query: String)
        fun onDestroy()
    }

    interface View: BaseView<Presenter>{
        fun showLoading()
        fun hideLoading()
        fun displayTotalResult(totalResult: String)
        fun showNews(newsList: List<News>)
        fun showError(errorMessage: String="")
    }
}