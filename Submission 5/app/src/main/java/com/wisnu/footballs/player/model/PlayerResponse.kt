package com.wisnu.footballs.player.model

import com.google.gson.annotations.SerializedName

data class PlayerResponse(
    @SerializedName("player")
    val player: List<Player>
)