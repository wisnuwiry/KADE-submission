package com.wisnu.footballs.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.wisnu.footballs.view.fragment.NextMatchFragment
import com.wisnu.footballs.view.fragment.PrevMatchFragment

class TabAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private val pages = listOf(
        PrevMatchFragment(),
        NextMatchFragment()
    )

    override fun getItem(position: Int): Fragment = pages[position] as Fragment

    override fun getCount(): Int = pages.size

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "Prev"
            1-> "Next"
            else -> "Error"
        }
    }
}