package com.wisnu.footballs.league.interfaces

import com.wisnu.footballs.league.model.League

interface LeagueView {
    fun showLoading()
    fun hideLoading()
    fun showData(data: List<League>?)
}