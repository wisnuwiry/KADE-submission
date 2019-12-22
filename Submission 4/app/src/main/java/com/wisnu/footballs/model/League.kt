package com.wisnu.footballs.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class League(
    @SerializedName("idLeague")
    val leagueId: String? = null,

    @SerializedName("strLeague")
    val leagueName: String? = null,

    @SerializedName("strDescriptionEN")
    val leagueDesc: String? = null,

    @SerializedName("strBadge")
    val leagueBadge: String? = null
) : Parcelable