package com.wisnu.footballs.presenter

import com.google.gson.Gson
import com.wisnu.footballs.api.ApiRepository
import com.wisnu.footballs.api.DBApi
import com.wisnu.footballs.model.EventResponse
import com.wisnu.footballs.util.CoroutineContextProvider
import com.wisnu.footballs.view.base.EventView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class EventPresenter(
    private val view: EventView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {
    fun getPrev(id: String) {
        view.showLoading()

        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository.doRequestAsync(DBApi.getPrevEvent(id)).await(),
                EventResponse::class.java
            )
            view.showData(data.events)
            view.hideLoading()
        }
    }

    fun getNext(id: String) {
        view.showLoading()

        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository.doRequestAsync(DBApi.getNextEvent(id)).await(),
                EventResponse::class.java
            )
            view.showData(data.events)
            view.hideLoading()
        }
    }

    fun search(q: String?) {
        view.showLoading()

        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository.doRequestAsync(DBApi.getSearch(q)).await(),
                EventResponse::class.java
            )
            data.events.let {
                val dataFinal = it.filter { it.strSport == "Soccer" }
                view.showData(dataFinal)
            }
            view.hideLoading()
        }
    }
}