package com.wisnu.footballs.view.base

import com.wisnu.footballs.model.Team

interface EventDetailView {
    fun dataHome(homeTeam: Team)
    fun dataAway(awayTeam: Team)
    fun showLoading()
    fun hideLoading()
}