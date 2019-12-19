package com.wisnu.footballs.service

import com.wisnu.footballs.model.EventResponse
import com.wisnu.footballs.model.LeagueResponse
import com.wisnu.footballs.model.SearchResponse
import com.wisnu.footballs.model.TeamResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("api/v1/json/1/search_all_leagues.php?s=Soccer")
    fun getLeague(): Call<LeagueResponse>

    @GET("api/v1/json/1/eventspastleague.php")
    fun getPrevEvent(@Query("id") id: String?): Call<EventResponse>

    @GET("api/v1/json/1/eventsnextleague.php")
    fun getNextEvent(@Query("id") id: String?): Call<EventResponse>

    @GET("api/v1/json/1/lookupteam.php")
    fun getTeamDetail(@Query("id") id: String?): Call<TeamResponse>

    @GET("api/v1/json/1/searchevents.php")
    fun getSearch(@Query("e") e: String? ): Call<SearchResponse>
}