package com.wisnu.footballs.search.model

import com.google.gson.annotations.SerializedName
import com.wisnu.footballs.event.model.Event

data class SearchResponse(
    @SerializedName("event")
    val event: List<Event>
)