package com.wisnu.footballs.view.base

import com.wisnu.footballs.model.Event

interface EventView {
    fun onSuccess(data: List<Event>)
    fun onError(message: String? = null)
}
