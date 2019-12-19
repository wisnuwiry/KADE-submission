package com.wisnu.footballs.api

import com.wisnu.footballs.service.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiRepository {

    fun create(): ApiService {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://www.thesportsdb.com")
            .build()

        return retrofit.create(ApiService::class.java)
    }
}