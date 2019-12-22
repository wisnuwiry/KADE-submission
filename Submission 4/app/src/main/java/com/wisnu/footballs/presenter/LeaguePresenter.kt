package com.wisnu.footballs.presenter

import com.google.gson.Gson
import com.wisnu.footballs.api.ApiRepository
import com.wisnu.footballs.api.DBApi
import com.wisnu.footballs.model.LeagueResponse
import com.wisnu.footballs.util.CoroutineContextProvider
import com.wisnu.footballs.view.base.LeagueView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LeaguePresenter(
    private val view: LeagueView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun getData() {
        view.showLoading()
        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository.doRequestAsync(DBApi.getLeague()).await(),
                LeagueResponse::class.java
            )
            
            view.showData(data.league)
            view.hideLoading()
        }
    }
}