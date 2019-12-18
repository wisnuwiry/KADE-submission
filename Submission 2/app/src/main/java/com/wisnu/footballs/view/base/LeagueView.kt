package com.wisnu.footballs.view.base

import com.wisnu.footballs.model.League

interface LeagueView {
    fun onSuccess(data: List<League>)
    fun onError(message: String? = null)
}