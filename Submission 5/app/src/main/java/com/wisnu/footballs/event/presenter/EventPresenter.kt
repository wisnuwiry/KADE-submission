package com.wisnu.footballs.event.presenter

import com.google.gson.Gson
import com.wisnu.footballs.api.ApiRepository
import com.wisnu.footballs.api.DBApi
import com.wisnu.footballs.event.model.EventResponse
import com.wisnu.footballs.search.model.SearchResponse
import com.wisnu.footballs.util.CoroutineContextProvider
import com.wisnu.footballs.event.interfaces.EventView
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
            view.showDataPrev(data.events)
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
            view.showDataNext(data.events)
            view.hideLoading()
        }
    }

    fun search(q: String?) {
        view.showLoading()

        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository.doRequestAsync(DBApi.getSearchEvent(q?.toLowerCase()?.trim())).await(),
                SearchResponse::class.java
            )
            data.event.let { it ->
                val dataFinal = it.filter { it.strSport == "Soccer" }
                view.showData(dataFinal)
            }
            view.hideLoading()
        }
    }
}