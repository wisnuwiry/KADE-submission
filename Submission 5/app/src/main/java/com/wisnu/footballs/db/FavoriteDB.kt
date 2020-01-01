package com.wisnu.footballs.db

data class FavoriteDB(
    val id: Long?,
    val eventId: String?,
    val eventName: String?,
    val homeTeam: String?,
    val awayTeam: String?,
    val homeScore: String?,
    val awayScore: String?,
    val dateEvent: String?,
    val idHomeTeam: String,
    val idAwayTeam: String
) {
    companion object {
        const val TABLE_FAVORITE: String = "TABLE_FAVORITE_EVENT"
        const val ID: String = "ID_"
        const val EVENT_ID: String = "EVENT_ID"
        const val EVENT_NAME: String = "EVENT_NAME"
        const val HOME_TEAM: String = "HOME_TEAM"
        const val AWAY_TEAM: String = "AWAY_TEAM"
        const val HOME_SCORE: String = "HOME_SCORE"
        const val AWAY_SCORE: String = "AWAY_SCORE"
        const val DATE_EVENT: String = "DATE_EVENT"
        const val ID_HOME_TEAM: String = "ID_HOME_TEAM"
        const val ID_AWAY_TEAM: String = "ID_AWAY_TEAM"
    }
}