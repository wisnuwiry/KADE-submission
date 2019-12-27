package com.wisnu.footballs.presenter

import com.google.gson.Gson
import com.wisnu.footballs.api.ApiRepository
import com.wisnu.footballs.model.Team
import com.wisnu.footballs.model.TeamResponse
import com.wisnu.footballs.util.CoroutineContextProviderTest
import com.wisnu.footballs.view.base.EventDetailView
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class EventDetailPresenterTest {

    @Mock
    private lateinit var view: EventDetailView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var apiResponse: Deferred<String>

    private lateinit var presenter: EventDetailPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = EventDetailPresenter(view, apiRepository, gson, CoroutineContextProviderTest())
    }

    @Test
    fun getHomeTeam() {
        val teams: MutableList<Team> = mutableListOf()
        val response = TeamResponse(teams)
        val team = "135170"

        runBlocking {
            Mockito.`when`(apiRepository.doRequestAsync(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)

            Mockito.`when`(apiResponse.await()).thenReturn("")

            Mockito.`when`(
                gson.fromJson(
                    "",
                    TeamResponse::class.java
                )
            ).thenReturn(response)

            presenter.getHomeTeam(team)
            Mockito.verify(view).showLoading()
        }
    }

    @Test
    fun getAwayTeam() {
        val teams: List<Team> = listOf()
        val response = TeamResponse(teams)
        val team = "135162"

        runBlocking {
            Mockito.`when`(apiRepository.doRequestAsync(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)

            Mockito.`when`(apiResponse.await()).thenReturn("")

            Mockito.`when`(
                gson.fromJson(
                    "",
                    TeamResponse::class.java
                )
            ).thenReturn(response)

            presenter.getAwayTeam(team)
            Mockito.verify(view).showLoading()
            Mockito.verify(view).hideLoading()
        }
    }
}