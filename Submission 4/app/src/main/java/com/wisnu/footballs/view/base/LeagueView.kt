package com.wisnu.footballs.view.base

import com.wisnu.footballs.model.League

interface LeagueView {
    fun showLoading()
    fun hideLoading()
    fun showData(data: List<League>?)
}