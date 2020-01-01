package com.wisnu.footballs.league.view.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.wisnu.footballs.R
import com.wisnu.footballs.api.ApiRepository
import com.wisnu.footballs.event.adapter.EventAdapter
import com.wisnu.footballs.event.interfaces.EventView
import com.wisnu.footballs.event.model.Event
import com.wisnu.footballs.event.presenter.EventPresenter
import com.wisnu.footballs.event.view.MatchDetailActivity
import com.wisnu.footballs.league.model.League
import com.wisnu.footballs.team.view.TeamFragment
import kotlinx.android.synthetic.main.fragment_match.*
import kotlinx.android.synthetic.main.fragment_match.view.*

/**
 * A simple [Fragment] subclass.
 */
class MatchFragment : Fragment(),
    EventView {

    private val eventsNext: MutableList<Event> = mutableListOf()
    private val eventsPrev: MutableList<Event> = mutableListOf()
    private lateinit var adapterNext: EventAdapter
    private lateinit var adapterPrev: EventAdapter
    private lateinit var presenter: EventPresenter
    private lateinit var progress: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_match, container, false)
        progress = view.findViewById(R.id.progress)

        val league = activity?.intent?.getParcelableExtra(TeamFragment.EXTRA_LEAGUE) as League

//        request API
        val request = ApiRepository()
        val gson = Gson()
        presenter = EventPresenter(this, request, gson)
        Log.e("LEAGUE", "LeagueId: ${league.leagueId}")
        presenter.getNext(league.leagueId.toString())
        presenter.getPrev(league.leagueId.toString())

        //        recyclerView Next Event
        adapterNext =
            EventAdapter(eventsNext) {
                val intent = Intent(activity, MatchDetailActivity::class.java)
                intent.putExtra("ID_EVENT", it)
                startActivity(intent)
            }
        view.rv_event_next.adapter = adapterNext
        view.rv_event_next.layoutManager = LinearLayoutManager(context)

        //        recyclerView Prev Event
        adapterPrev =
            EventAdapter(eventsPrev) {
                val intent = Intent(activity, MatchDetailActivity::class.java)
                intent.putExtra("ID_EVENT", it)
                startActivity(intent)
            }
        view.rv_event_prev.adapter = adapterPrev
        view.rv_event_prev.layoutManager = LinearLayoutManager(context)

        view.swipe.setOnRefreshListener {
            view.swipe.isRefreshing = false
            presenter.getPrev(league.leagueId.toString())
            presenter.getNext(league.leagueId.toString())
        }

        return view
    }

    override fun showLoading() {
        progress.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progress.visibility = View.INVISIBLE
    }

    override fun showData(data: List<Event>?) {}

    override fun showDataNext(data: List<Event>?) {
        print("Data for Next Event $data")
        if (data != null) {
            eventsNext.addAll(data)
        } else {
            card_next.visibility = View.GONE
            rv_event_next.visibility = View.GONE
        }
        adapterNext.notifyDataSetChanged()
    }

    override fun showDataPrev(data: List<Event>?) {
        if (data != null) {
            eventsPrev.addAll(data)
        }
        adapterPrev.notifyDataSetChanged()
    }
}
