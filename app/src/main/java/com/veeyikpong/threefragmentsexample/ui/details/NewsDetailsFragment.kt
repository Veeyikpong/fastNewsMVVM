package com.veeyikpong.threefragmentsexample.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.veeyikpong.threefragmentsexample.R
import com.veeyikpong.threefragmentsexample.SampleActivity
import com.veeyikpong.threefragmentsexample.search.SearchFragment
import kotlinx.android.synthetic.main.activity_sample.*
import kotlinx.android.synthetic.main.fragment_second.*

class NewsDetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_next.setOnClickListener {
            ((activity) as SampleActivity).fragmentContainer.addFragment(SearchFragment())
        }
    }
}
