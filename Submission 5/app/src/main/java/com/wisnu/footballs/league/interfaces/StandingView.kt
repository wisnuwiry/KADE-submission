package com.wisnu.footballs.league.interfaces

import com.wisnu.footballs.league.model.Standing

interface StandingView {
    fun showLoading()
    fun hideLoading()
    fun showData(data: List<Standing>?)
}