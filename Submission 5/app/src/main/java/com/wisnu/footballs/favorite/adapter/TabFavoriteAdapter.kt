package com.wisnu.footballs.favorite.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.wisnu.footballs.favorite.view.FavoriteEventFragment
import com.wisnu.footballs.favorite.view.FavoriteTeamFragment

class TabFavoriteAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private val pages = listOf(
        FavoriteEventFragment(),
        FavoriteTeamFragment()
    )

    override fun getItem(position: Int): Fragment = pages[position] as Fragment

    override fun getCount(): Int = pages.size

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Event"
            1 -> "Team"
            else -> "Error"
        }
    }
}