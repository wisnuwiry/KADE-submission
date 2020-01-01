package com.wisnu.footballs.search.presenter

import com.google.gson.Gson
import com.wisnu.footballs.api.ApiRepository
import com.wisnu.footballs.api.DBApi
import com.wisnu.footballs.search.interfaces.SearchView
import com.wisnu.footballs.search.model.SearchResponse
import com.wisnu.footballs.team.model.TeamResponse
import com.wisnu.footballs.util.CoroutineContextProvider
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SearchPresenter(
    private val view: SearchView, private val apiRepository: ApiRepository, private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {
    fun searchEvent(q: String?) {
        view.showLoading()
        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository.doRequestAsync(DBApi.getSearchEvent(q?.toLowerCase()?.trim())).await(),
                SearchResponse::class.java
            )
            data.event.let { it ->
                val dataFinal = it.filter { it.strSport == "Soccer" }
                view.showEvent(dataFinal)
            }
            view.hideLoading()
        }
    }

    fun searchTeam(q: String?) {
        view.showLoading()
        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository.doRequestAsync(DBApi.getSearchTeam(q?.toLowerCase()?.trim())).await(),
                TeamResponse::class.java
            )
            if (data.teams.isNotEmpty()) {
                data.teams.let { it ->
                    val dataFinal = it.filter { it.strSport == "Soccer" }
                    view.showTeam(dataFinal)
                }
            }
            view.hideLoading()
        }
    }
}