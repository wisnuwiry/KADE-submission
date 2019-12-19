package com.wisnu.footballs.presenter

import com.wisnu.footballs.api.ApiRepository
import com.wisnu.footballs.view.base.LeagueView
import com.wisnu.footballs.model.LeagueResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LeaguePresenter(private val view: LeagueView) {

    fun getData() {
        val leagueService = ApiRepository.create()
        leagueService.getLeague().enqueue(object : Callback<LeagueResponse> {
            override fun onFailure(call: Call<LeagueResponse>, t: Throwable) {
                view.onError("Error: " + t.message)
            }

            override fun onResponse(
                call: Call<LeagueResponse>,
                response: Response<LeagueResponse>
            ) {
                if (response.isSuccessful) {
                    response.body()?.league?.let {
                        view.onSuccess(it)
                    }
                }
            }
        })
    }
}