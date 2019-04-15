package com.veeyikpong.threefragmentsexample.ui.details

import android.os.Bundle
import com.veeyikpong.threefragmentsexample.ui.base.BaseView

class NewsDetailsContract{
    interface Presenter{
        fun init(bundle: Bundle)
    }

    interface View: BaseView<Presenter> {
        fun showLoading()
        fun hideLoading()
        fun setImage(imageURL: String)
        fun setTitle(title: String)
        fun setAuthor(author: String)
        fun setDescription(description: String)
        fun setReadMoreURL(url: String)
        fun showDetails()
        fun showError()
        fun close()
    }
}