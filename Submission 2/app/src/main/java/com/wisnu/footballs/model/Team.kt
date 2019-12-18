package com.wisnu.footballs.model

import com.google.gson.annotations.SerializedName

data class Team(
    @SerializedName("idTeam")
    val idTeam: String? = null,

    @SerializedName("strTeam")
    var teamName: String? = null,

    @SerializedName("strDescriptionEN")
    var teamDescription: String? = null,

    @SerializedName("strTeamBadge")
    var teamBadge: String? = null
)