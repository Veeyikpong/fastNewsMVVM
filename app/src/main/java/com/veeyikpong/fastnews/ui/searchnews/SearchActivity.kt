package com.veeyikpong.fastnews.ui.searchnews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.veeyikpong.easyfragmentcontainer.FragmentContainer
import com.veeyikpong.fastnews.R
import com.veeyikpong.fastnews.ui.searchnews.details.NewsDetailsFragment
import com.veeyikpong.fastnews.ui.searchnews.search.SearchFragment

import kotlinx.android.synthetic.main.activity_main.*

class SearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        fragmentContainer.addFragment(SearchFragment())
        fragmentContainer.addFragmentListener(object : FragmentContainer.FragmentListener {
            override fun onFragmentShow(f: Fragment) {
                updateTitle(f)
            }

            override fun onFragmentDestroyed(f: Fragment) {

            }
        })
    }

    private fun updateTitle(f: Fragment) {
        when (f) {
            is SearchFragment -> {
                supportActionBar!!.title = getString(R.string.search_news)
            }
            is NewsDetailsFragment -> {
                supportActionBar!!.title = getString(R.string.news_details)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            android.R.id.home -> {
                onBackPressed()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if (fragmentContainer.back())
            return

        finish()
    }
}
