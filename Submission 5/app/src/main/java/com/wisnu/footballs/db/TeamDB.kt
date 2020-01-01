package com.wisnu.footballs.db

data class TeamDB(
    val id: Long?,
    val idTeam: String?,
    val teamName: String?,
    val teamLeague: String?,
    val teamDescription: String?,
    val teamBadge: String
){
    companion object{
        const val TABLE_FAVORITE: String = "TABLE_FAVORITE_TEAM"
        const val ID: String = "ID_"
        const val ID_TEAM: String = "ID_TEAM"
        const val TEAM_NAME: String = "TEAM_NAME"
        const val TEAM_LEAGUE:String = "TEAM_LEAGUE"
        const val TEAM_DESCRIPTION:String = "TEAM_DESCRIPTION"
        const val TEAM_BADGE:String = "TEAM_BADGE"

    }
}