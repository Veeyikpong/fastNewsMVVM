package com.veeyikpong.fastnews.ui.searchnews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.veeyikpong.fastnews.R
import com.veeyikpong.fastnews.viewmodels.SearchViewModel

class SearchActivity : AppCompatActivity() {

    private lateinit var viewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        viewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        Navigation.findNavController(this,R.id.nav_host).addOnDestinationChangedListener { controller, destination, arguments ->
            supportActionBar!!.title = destination.label
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
        if(!Navigation.findNavController(this,R.id.nav_host).navigateUp()){
            finish()
        }
    }
}
