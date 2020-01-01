package com.wisnu.footballs.player.interfaces

import com.wisnu.footballs.player.model.Player

interface PlayerView {
    fun showLoading()
    fun hideLoading()
    fun showData(data: List<Player>?)
}