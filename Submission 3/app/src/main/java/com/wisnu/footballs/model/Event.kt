package com.wisnu.footballs.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Event(
    @SerializedName("idEvent")
    val eventId: String? = null,

    @SerializedName("strEvent")
    val eventName: String? = null,

    @SerializedName("strHomeTeam")
    val homeTeam: String? = null,

    @SerializedName("strAwayTeam")
    val awayTeam: String? = "",

    @SerializedName("intHomeScore")
    var homeScore: String? = " ",
    @SerializedName("intAwayScore")
    var awayScore: String? = " ",

    @SerializedName("dateEvent")
    val dateEvent: String? = null,

    @SerializedName("idHomeTeam")
    var idHomeTeam: String? = null,

    @SerializedName("idAwayTeam")
    var idAwayTeam: String? = null,

    @SerializedName("strSport")
    var strSport: String? = null
) : Parcelable