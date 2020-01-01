package com.wisnu.footballs.team.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Team(
    @SerializedName("idTeam")
    val idTeam: String? = null,

    @SerializedName("strTeam")
    var teamName: String? = null,

    @SerializedName("strLeague")
    val teamLeague: String? = null,

    @SerializedName("strDescriptionEN")
    var teamDescription: String? = null,

    @SerializedName("strTeamBadge")
    var teamBadge: String? = null,

    @SerializedName("strSport")
    val strSport: String? = null
) : Parcelable