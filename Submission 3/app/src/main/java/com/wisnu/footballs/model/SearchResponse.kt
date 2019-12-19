package com.wisnu.footballs.model

import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("event")
    val events: List<Event>
)