package com.wisnu.footballs.search.interfaces

import com.wisnu.footballs.event.model.Event
import com.wisnu.footballs.team.model.Team

interface SearchView {
    fun showLoading()
    fun hideLoading()
    fun showEvent(data: List<Event>?)
    fun showTeam(data: List<Team>)
}