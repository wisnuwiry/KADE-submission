package com.wisnu.footballs.league.model

import com.google.gson.annotations.SerializedName

data class StandingResponse(
    @SerializedName("table")
    val table: List<Standing>
)