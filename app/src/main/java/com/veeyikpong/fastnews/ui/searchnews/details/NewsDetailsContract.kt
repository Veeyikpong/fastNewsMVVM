package com.veeyikpong.fastnews.ui.searchnews.details

import android.os.Bundle
import com.veeyikpong.fastnews.ui.base.BaseView

class NewsDetailsContract{
    interface Presenter{
        fun init(bundle: Bundle)
    }

    interface View: BaseView<Presenter> {
        fun setImage(imageURL: String)
        fun setTitle(title: String)
        fun setAuthor(author: String)
        fun setDescription(description: String)
        fun setReadMoreURL(url: String)
        fun showError()
        fun close()
    }
}