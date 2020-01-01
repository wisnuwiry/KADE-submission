package com.wisnu.footballs.favorite.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.wisnu.footballs.R
import com.wisnu.footballs.favorite.adapter.TabFavoriteAdapter
import kotlinx.android.synthetic.main.fragment_favorite.view.*

/**
 * A simple [Fragment] subclass.
 */
class FavoriteFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_favorite, container, false)

        view.vp_favorite.adapter = TabFavoriteAdapter(childFragmentManager)
        view.tab_favorite.setupWithViewPager(view.vp_favorite)

        return view
    }


}
