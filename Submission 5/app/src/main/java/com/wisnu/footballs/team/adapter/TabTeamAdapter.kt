package com.wisnu.footballs.team.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.wisnu.footballs.player.view.PlayerFragment
import com.wisnu.footballs.team.view.fragment.TeamDetailOverviewFragment

class TabTeamAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private val pages = listOf(
        TeamDetailOverviewFragment(),
        PlayerFragment()
    )

    override fun getItem(position: Int): Fragment = pages[position] as Fragment

    override fun getCount(): Int = pages.size

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Team Info"
            1 -> "Player"
            else -> "Error"
        }
    }
}