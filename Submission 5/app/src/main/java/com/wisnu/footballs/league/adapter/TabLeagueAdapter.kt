package com.wisnu.footballs.league.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.wisnu.footballs.league.view.fragment.StandingFragment
import com.wisnu.footballs.team.view.TeamFragment
import com.wisnu.footballs.league.view.fragment.MatchFragment

class TabLeagueAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private val pages = listOf(
        MatchFragment(),
        StandingFragment(),
        TeamFragment()
    )

    override fun getItem(position: Int): Fragment = pages[position] as Fragment

    override fun getCount(): Int = pages.size

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Match"
            1 -> "Standings"
            2 -> "Team"
            else -> "Error"
        }
    }
}