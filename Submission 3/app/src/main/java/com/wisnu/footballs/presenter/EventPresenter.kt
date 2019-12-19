package com.wisnu.footballs.presenter

import com.wisnu.footballs.api.ApiRepository
import com.wisnu.footballs.model.EventResponse
import com.wisnu.footballs.model.SearchResponse
import com.wisnu.footballs.view.base.EventView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EventPresenter(private val view: EventView) {
    fun getPrev(id: String) {
        val eventService = ApiRepository.create()
        eventService.getPrevEvent(id)
            .enqueue(object : Callback<EventResponse> {
                override fun onFailure(call: Call<EventResponse>, t: Throwable) {
                    view.onError("Error: " + t.message)
                }

                override fun onResponse(
                    call: Call<EventResponse>,
                    response: Response<EventResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.events?.let {
                            view.onSuccess(it)
                        }
                    }
                }
            })
    }

    fun getNext(id: String) {
        val eventService = ApiRepository.create()
        eventService.getNextEvent(id)
            .enqueue(object : Callback<EventResponse> {
                override fun onFailure(call: Call<EventResponse>, t: Throwable) {
                    view.onError("Error: " + t.message)
                }

                override fun onResponse(
                    call: Call<EventResponse>,
                    response: Response<EventResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.events?.let {
                            view.onSuccess(it)
                        }
                    }
                }
            })
    }

    fun search(q: String?){
        val eventService = ApiRepository.create()
        eventService.getSearch(q)
            .enqueue(object : Callback<SearchResponse> {
                override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                    view.onError("Error: " + t.message)
                }

                override fun onResponse(
                    call: Call<SearchResponse>,
                    response: Response<SearchResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.events?.let { it ->
                            val data = it.filter { it.strSport == "Soccer" }
                            view.onSuccess(data)
                        }
                    }
                }
            })
    }
}