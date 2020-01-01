package com.wisnu.footballs.player.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Player(
    @SerializedName("idPlayer")
    val id: String? = null,

    @SerializedName("strPlayer")
    val name: String? = null,

    @SerializedName("dateBorn")
    val date: String? = null,

    @SerializedName("strBirthLocation")
    val birthLocation: String? = null,

    @SerializedName("strNumber")
    val number: String? = null,

    @SerializedName("strHeight")
    val height: String? = null,

    @SerializedName("strDescriptionEN")
    val desc: String? = null,

    @SerializedName("strPosition")
    val position: String? = null,

    @SerializedName("strCutout")
    val avatar: String? = null
) : Parcelable