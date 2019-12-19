package com.wisnu.footballs.presenter

import com.wisnu.footballs.api.ApiRepository
import com.wisnu.footballs.model.TeamResponse
import com.wisnu.footballs.view.base.EventDetailView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EventDetailPresenter(private val view: EventDetailView) {
    private val service = ApiRepository.create()
    fun getHomeTeam(homeTeamId: String) {

        service.getTeamDetail(homeTeamId)
            .enqueue(object : Callback<TeamResponse> {
                override fun onFailure(call: Call<TeamResponse>, t: Throwable) {
                    view.onError("Error: " + t.message)
                }

                override fun onResponse(
                    call: Call<TeamResponse>,
                    response: Response<TeamResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.teams?.let {
                            view.dataHome(it[0])
                        }

                    }
                }
            })
    }

    fun getAwayTeam(awayTeamId: String) {
        service.getTeamDetail(awayTeamId)
            .enqueue(object : Callback<TeamResponse> {
                override fun onFailure(call: Call<TeamResponse>, t: Throwable) {
                    view.onError("Error: " + t.message)
                }

                override fun onResponse(
                    call: Call<TeamResponse>,
                    response: Response<TeamResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.teams?.let {
                            view.dataAway(it[0])
                        }

                    }
                }
            })
    }
}
