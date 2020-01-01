package com.wisnu.footballs.search.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.wisnu.footballs.search.view.fragment.SearchEventFragment
import com.wisnu.footballs.search.view.fragment.SearchTeamFragment

class TabSearchAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private val pages = listOf(
        SearchEventFragment(),
        SearchTeamFragment()
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