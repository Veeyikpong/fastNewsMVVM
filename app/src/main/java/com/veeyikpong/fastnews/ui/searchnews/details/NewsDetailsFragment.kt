package com.veeyikpong.fastnews.ui.searchnews.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.veeyikpong.fastnews.R
import kotlinx.android.synthetic.main.fragment_news_details.*

class NewsDetailsFragment : Fragment(),NewsDetailsContract.View {

    private lateinit var presenter: NewsDetailsContract.Presenter

    override fun setPresenter(presenter: NewsDetailsContract.Presenter) {
        this.presenter = presenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setPresenter(NewsDetailsPresenter(this))
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

        presenter.init(arguments!!)
    }

    override fun showError() {
        Toast.makeText(requireContext(),getString(R.string.something_went_wrong),Toast.LENGTH_SHORT).show()
    }

    override fun close() {
        requireActivity().onBackPressed()
    }

    override fun setImage(imageURL: String) {
        Glide
            .with(this)
            .load(imageURL)
            .into(img_news)
    }

    override fun setTitle(title: String) {
        tv_title.text = title
    }

    override fun setAuthor(author: String) {
        tv_author.text = getString(R.string.author_by, author)
    }

    override fun setDescription(description: String) {
        tv_description.text = description
    }

    override fun setReadMoreURL(url: String) {
        tv_readMore.text = getString(R.string.read_more_at,url)
    }
}
