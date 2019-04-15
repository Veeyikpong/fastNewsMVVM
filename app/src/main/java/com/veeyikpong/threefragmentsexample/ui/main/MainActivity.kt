package com.veeyikpong.threefragmentsexample


import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.veeyikpong.easyfragmentcontainer.FragmentContainer
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.fragment_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fragmentContainer.replaceFragment(MainFragment())
        fragmentContainer.addFragmentListener(object: FragmentContainer.FragmentListener{
            override fun onFragmentShow(f: Fragment) {
                updateTitle(f)
            }

            override fun onFragmentDestroyed(f: Fragment) {

            }
        })
    }

    private fun updateTitle(f: Fragment){
        when(f){
            is MainFragment->{
                supportActionBar!!.title = getString(R.string.top_headlines)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId){
            android.R.id.home->{onBackPressed()}
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if(fragmentContainer.back())
            return

        finish()
    }
}
