package com.wisnu.footballs.team.interfaces

import com.wisnu.footballs.team.model.Team

interface TeamView {
    fun showLoading()
    fun hideLoading()
    fun showData(data: List<Team>?)
}