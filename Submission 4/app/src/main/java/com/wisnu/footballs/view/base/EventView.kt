package com.wisnu.footballs.view.base

import com.wisnu.footballs.model.Event

interface EventView {
    fun showLoading()
    fun hideLoading()
    fun showData(data: List<Event>)
}
