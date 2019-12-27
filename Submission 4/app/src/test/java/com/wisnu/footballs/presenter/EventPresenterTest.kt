package com.wisnu.footballs.presenter

import com.google.gson.Gson
import com.wisnu.footballs.api.ApiRepository
import com.wisnu.footballs.api.DBApi
import com.wisnu.footballs.model.Event
import com.wisnu.footballs.model.EventResponse
import com.wisnu.footballs.util.CoroutineContextProviderTest
import com.wisnu.footballs.view.base.EventView
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class EventPresenterTest {

    @Mock
    private lateinit var view: EventView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var apiResponse: Deferred<String>

    private lateinit var presenter: EventPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = EventPresenter(view, apiRepository, gson, CoroutineContextProviderTest())
    }

    @Test
    fun getPrev() {
        val events: MutableList<Event> = mutableListOf()
        val response = EventResponse(events)
        val league = "4328"

        runBlocking {
            Mockito.`when`(apiRepository.doRequestAsync(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)

            Mockito.`when`(apiResponse.await()).thenReturn("")

            Mockito.`when`(
                gson.fromJson(
                    apiRepository.doRequestAsync(DBApi.getPrevEvent(league)).await(),
                    EventResponse::class.java
                )
            ).thenReturn(response)

            presenter.getPrev(league)
            Mockito.verify(view).showLoading()
            Mockito.verify(view).showData(events)
            Mockito.verify(view).hideLoading()
        }
    }

    @Test
    fun getNext() {
        val events: MutableList<Event> = mutableListOf()
        val response = EventResponse(events)
        val league = "4328"

        runBlocking {
            Mockito.`when`(apiRepository.doRequestAsync(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)

            Mockito.`when`(apiResponse.await()).thenReturn("")

            Mockito.`when`(
                gson.fromJson(
                    apiRepository.doRequestAsync(DBApi.getNextEvent(league)).await(),
                    EventResponse::class.java
                )
            ).thenReturn(response)

            presenter.getNext(league)
            Mockito.verify(view).showLoading()
            Mockito.verify(view).showData(events)
            Mockito.verify(view).hideLoading()
        }
    }

    @Test
    fun search() {
        val events: MutableList<Event> = mutableListOf()
        val response = EventResponse(events)
        val search = "Barcelona"

        runBlocking {
            Mockito.`when`(apiRepository.doRequestAsync(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)

            Mockito.`when`(apiResponse.await()).thenReturn("")

            Mockito.`when`(
                gson.fromJson(
                    apiRepository.doRequestAsync(DBApi.getSearch(search)).await(),
                    EventResponse::class.java
                )
            ).thenReturn(response)

            presenter.getPrev(search)
            Mockito.verify(view).showLoading()
            Mockito.verify(view).showData(events)
            Mockito.verify(view).hideLoading()
        }
    }
}