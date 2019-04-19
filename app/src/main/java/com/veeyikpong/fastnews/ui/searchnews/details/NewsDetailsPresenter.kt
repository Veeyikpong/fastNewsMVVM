package com.veeyikpong.fastnews.ui.searchnews.details

import android.os.Bundle
import com.veeyikpong.fastnews.AppConstants
import com.veeyikpong.fastnews.api.response.News
import com.veeyikpong.fastnews.ui.base.BasePresenter

class NewsDetailsPresenter(val view:NewsDetailsContract.View):BasePresenter(),NewsDetailsContract.Presenter{
    override fun init(bundle: Bundle) {
        val news = bundle.getSerializable(AppConstants.BUNDLE_KEY_NEWS) as News

        if(news == null){
            view.showError()
            view.close()
            return
        }

        view.setImage(news.imageURL)
        view.setTitle(news.title)
        view.setAuthor(news.author)
        view.setDescription(news.body)
        view.setReadMoreURL(news.url)
    }
}