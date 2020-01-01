package com.wisnu.footballs.search.view.fragment


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.wisnu.footballs.R
import com.wisnu.footballs.api.ApiRepository
import com.wisnu.footballs.event.adapter.EventAdapter
import com.wisnu.footballs.event.model.Event
import com.wisnu.footballs.event.view.MatchDetailActivity
import com.wisnu.footballs.search.interfaces.SearchView
import com.wisnu.footballs.search.presenter.SearchPresenter
import com.wisnu.footballs.team.model.Team
import kotlinx.android.synthetic.main.fragment_search_event.view.*

/**
 * A simple [Fragment] subclass.
 */
class SearchEventFragment : Fragment(), SearchView {

    private var events: MutableList<Event> = mutableListOf()
    private lateinit var mView: View
    private lateinit var adapter: EventAdapter
    private lateinit var presenter: SearchPresenter
    private lateinit var query: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(R.layout.fragment_search_event, container, false)

        val request = ApiRepository()
        val gson = Gson()
        presenter = SearchPresenter(this, request, gson)

        adapter = EventAdapter(events) {
            val intent = Intent(activity, MatchDetailActivity::class.java)
            intent.putExtra("ID_EVENT", it)
            startActivity(intent)
        }
        mView.rv_search_event.layoutManager = LinearLayoutManager(activity)
        mView.rv_search_event.adapter = adapter

        mView.search_event.setOnQueryTextListener(object :
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(q: String): Boolean {
                query = q
                presenter.searchEvent(q)
                return true
            }

            override fun onQueryTextChange(q: String): Boolean {
                query = q
                presenter.searchEvent(q)
                return false
            }
        })
        mView.swipe.setOnRefreshListener {
            presenter.searchEvent(query)
        }
        return mView
    }

    override fun showLoading() {
        mView.swipe.isRefreshing = true
    }

    override fun hideLoading() {
        mView.swipe.isRefreshing = false
    }

    override fun showEvent(data: List<Event>?) {
        if (data != null) {
            events.addAll(data)
        }
        adapter.notifyDataSetChanged()
    }

    override fun showTeam(data: List<Team>) {}
}
