package com.veeyikpong.fastnews.ui.searchnews.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.veeyikpong.fastnews.*
import com.veeyikpong.fastnews.ui.searchnews.search.adapter.NewsAdapter
import kotlinx.android.synthetic.main.fragment_search.*
import android.widget.EditText
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.veeyikpong.fastnews.api.Resource
import com.veeyikpong.fastnews.api.response.News
import com.veeyikpong.fastnews.viewmodels.SearchViewModel


class SearchFragment : Fragment() {

    private lateinit var viewModel: SearchViewModel
    private lateinit var newsAdapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(activity!!).get(SearchViewModel::class.java)

        newsAdapter = NewsAdapter(requireContext(),ArrayList())
        newsAdapter.setOnItemClickListener(object: NewsAdapter.OnItemClickListener{
            override fun onItemClick(news: News) {
                viewModel.selectNews(news)
                Navigation.findNavController(view!!).navigate(R.id.newsDetailsFragment)
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.getNewsResponse().observe(this, Observer { resources->
            when(resources.status){
                Resource.Status.SUCCESS->{
                    hideLoading()
                    ll_result.visibility = View.VISIBLE
                    newsAdapter.setList(resources.data!!.newsList)
                    tv_totalResult.text = getString(R.string.results_found,resources.data!!.totalResults.toString())
                }
                Resource.Status.ERROR->{
                    hideLoading()
                    resources.errorMessage?.let { showError(it) }
                }
                Resource.Status.LOADING->{
                    showLoading()
                }
            }
        })

        btn_search.setOnClickListener {
            search()
        }

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                search()

                return false
            }
        })

        with(rv_news){
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(DividerItemDecoration(requireContext(),DividerItemDecoration.VERTICAL))
        }
    }

    fun search(){
        if(validate()){
            searchView.clearFocus()
            viewModel.searchNews(searchView.query.toString())
        }
    }

    fun validate(): Boolean{
        var validated = true

        if(searchView.query.isBlank()){
            validated = false
            searchView.context.resources.getIdentifier("android:id/search_src_text", null, null)
            val editText = searchView.findViewById(id) as EditText
            editText.error = getString(R.string.err_search_text_empty)
        }

        return validated
    }

    fun displayTotalResult(totalResult: String) {
        tv_totalResult.text = getString(R.string.results_found,totalResult)
    }

    fun showError(errorMessage: String) {
        if(errorMessage.isBlank()) {
            Toast.makeText(requireContext(), getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
        }
    }

    fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    fun hideLoading() {
        progressBar.visibility = View.GONE
    }
}
