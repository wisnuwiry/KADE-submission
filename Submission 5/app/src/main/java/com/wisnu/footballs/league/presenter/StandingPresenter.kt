package com.wisnu.footballs.league.presenter

import com.google.gson.Gson
import com.wisnu.footballs.api.ApiRepository
import com.wisnu.footballs.api.DBApi
import com.wisnu.footballs.league.interfaces.StandingView
import com.wisnu.footballs.league.model.StandingResponse
import com.wisnu.footballs.util.CoroutineContextProvider
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class StandingPresenter(
    private val view: StandingView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun getData(id: String?) {
        view.showLoading()
        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository.doRequestAsync(DBApi.getStanding(id)).await(),
                StandingResponse::class.java
            )

            if (data.table.isNotEmpty()) {
                view.showData(data.table)
            }
            view.hideLoading()
        }
    }
}