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

    fun getSearch(q: String?):String{
        return BuildConfig.BASE_URL + "api/v1/json/1/searchevents.php?e=" + q
    }
}
