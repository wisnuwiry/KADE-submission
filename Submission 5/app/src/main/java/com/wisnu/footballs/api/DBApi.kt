package com.wisnu.footballs.api

import com.wisnu.footballs.BuildConfig

object DBApi {
    fun getLeague(): String {
        return BuildConfig.BASE_URL + "api/v1/json/1/search_all_leagues.php?s=Soccer"
    }

    fun getPrevEvent(id: String?): String {
        return BuildConfig.BASE_URL + "api/v1/json/1/eventspastleague.php?id=" + id
    }

    fun getNextEvent(id: String?): String {
        return BuildConfig.BASE_URL + "api/v1/json/1/eventsnextleague.php?id=" + id
    }

    fun getTeamDetail(id: String?): String {
        return BuildConfig.BASE_URL + "api/v1/json/1/lookupteam.php?id=" + id
    }

    fun getSearchEvent(q: String?): String {
        return BuildConfig.BASE_URL + "api/v1/json/1/searchevents.php?e=" + q
    }

    fun getSearchTeam(q: String?): String{
        return BuildConfig.BASE_URL + "api/v1/json/1/searchteams.php?t=" + q
    }

    fun getTeamLeague(id: String?): String {
        return BuildConfig.BASE_URL + "api/v1/json/1/lookup_all_teams.php?id=" + id
    }

    fun getStanding(id: String?):String{
        return BuildConfig.BASE_URL + "api/v1/json/1/lookuptable.php?l=" + id
    }

    fun getPlayer(id: String?):String{
        return BuildConfig.BASE_URL + "api/v1/json/4012931/lookup_all_players.php?id=" + id
    }

}
