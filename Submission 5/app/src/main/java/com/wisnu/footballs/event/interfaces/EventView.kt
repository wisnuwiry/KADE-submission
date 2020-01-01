package com.wisnu.footballs.event.interfaces

import com.wisnu.footballs.event.model.Event

interface EventView {
    fun showLoading()
    fun hideLoading()
    fun showData(data: List<Event>?)
    fun showDataNext(data: List<Event>?)
    fun showDataPrev(data: List<Event>?)
}
