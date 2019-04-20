package com.veeyikpong.fastnews.ui.searchnews.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.veeyikpong.fastnews.R
import com.veeyikpong.fastnews.viewmodels.SearchViewModel
import kotlinx.android.synthetic.main.fragment_news_details.*

class NewsDetailsFragment : Fragment() {

    private lateinit var viewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(activity!!).get(SearchViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getSelectedNews().observe(this, Observer {news->
            if(news == null){
                showError()
                close()
                return@Observer
            }

            setTitle(news.title)
            setAuthor(news.author)
            setImage(news.imageURL)
            setDescription(news.body)
            setReadMoreURL(news.url)
        })
    }

    fun showError() {
        Toast.makeText(requireContext(),getString(R.string.something_went_wrong),Toast.LENGTH_SHORT).show()
    }

    fun close() {
        requireActivity().onBackPressed()
    }

    fun setImage(imageURL: String) {
        Glide
            .with(this)
            .load(imageURL)
            .into(img_news)
    }

    fun setTitle(title: String) {
        tv_title.text = title
    }

    fun setAuthor(author: String) {
        tv_author.text = getString(R.string.author_by, author)
    }

    fun setDescription(description: String) {
        tv_description.text = description
    }

    fun setReadMoreURL(url: String) {
        tv_readMore.text = getString(R.string.read_more_at,url)
    }
}
