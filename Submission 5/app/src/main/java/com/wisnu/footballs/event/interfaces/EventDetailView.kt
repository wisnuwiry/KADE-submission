package com.wisnu.footballs.event.interfaces

import com.wisnu.footballs.team.model.Team

interface EventDetailView {
    fun dataHome(homeTeam: Team)
    fun dataAway(awayTeam: Team)
    fun showLoading()
    fun hideLoading()
}