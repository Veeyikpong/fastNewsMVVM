package com.veeyikpong.fastnews.ui.details

import android.os.Bundle
import com.veeyikpong.fastnews.AppConstants
import com.veeyikpong.fastnews.api.response.News
import com.veeyikpong.fastnews.ui.base.BasePresenter

class NewsDetailsPresenter(val view:NewsDetailsFragment):BasePresenter(),NewsDetailsContract.Presenter{
    override fun init(bundle: Bundle) {
        if(bundle == null){
            view.showError()
            view.close()
            return
        }

        val news = bundle.getSerializable(AppConstants.BUNDLE_KEY_NEWS) as News

        view.setImage(news.imageURL)
        view.setTitle(news.title)
        view.setAuthor(news.author)
        view.setDescription(news.body)
        view.setReadMoreURL(news.url)
    }
}