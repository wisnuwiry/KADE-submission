package com.wisnu.footballs.league.model

import com.google.gson.annotations.SerializedName
import com.wisnu.footballs.league.model.League

data class LeagueResponse(
    @SerializedName("countrys")
    val league: List<League>
)