package com.wisnu.footballs.team.presenter

import com.google.gson.Gson
import com.wisnu.footballs.api.ApiRepository
import com.wisnu.footballs.api.DBApi
import com.wisnu.footballs.team.interfaces.TeamView
import com.wisnu.footballs.team.model.TeamResponse
import com.wisnu.footballs.util.CoroutineContextProvider
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TeamPresenter(
    private val view: TeamView, private val apiRepository: ApiRepository, private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {
    fun getData(id: String?) {
        view.showLoading()
        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository.doRequestAsync(DBApi.getTeamLeague(id)).await(),
                TeamResponse::class.java
            )
            view.showData(data.teams)
            view.hideLoading()
        }
    }
}