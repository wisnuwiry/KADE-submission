package com.wisnu.footballs.search.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.wisnu.footballs.R
import com.wisnu.footballs.search.adapter.TabSearchAdapter
import kotlinx.android.synthetic.main.fragment_search.view.*

/**
 * A simple [Fragment] subclass.
 */
class SearchFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)

        view.vp_search.adapter = TabSearchAdapter(childFragmentManager)
        view.tab_search.setupWithViewPager(view.vp_search)

        return view
    }
}
