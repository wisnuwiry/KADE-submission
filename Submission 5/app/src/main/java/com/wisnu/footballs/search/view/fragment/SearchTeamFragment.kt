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
import com.wisnu.footballs.event.model.Event
import com.wisnu.footballs.search.interfaces.SearchView
import com.wisnu.footballs.search.presenter.SearchPresenter
import com.wisnu.footballs.team.adapter.TeamAdapter
import com.wisnu.footballs.team.model.Team
import com.wisnu.footballs.team.view.TeamDetailActivity
import kotlinx.android.synthetic.main.fragment_search_team.view.*

/**
 * A simple [Fragment] subclass.
 */
class SearchTeamFragment : Fragment(), SearchView {

    private var teams: MutableList<Team> = mutableListOf()
    private lateinit var mView: View
    private lateinit var adapter: TeamAdapter
    private lateinit var presenter: SearchPresenter
    private lateinit var query: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_search_team, container, false)
        val request = ApiRepository()
        val gson = Gson()
        presenter = SearchPresenter(this, request, gson)

        adapter = TeamAdapter(teams) {
            val intent = Intent(activity, TeamDetailActivity::class.java)
            intent.putExtra(TeamDetailActivity.EXTRA_TEAM, it)
            startActivity(intent)
        }
        mView.rv_search_team.layoutManager = LinearLayoutManager(activity)
        mView.rv_search_team.adapter = adapter

        mView.search_team.setOnQueryTextListener(object :
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(q: String): Boolean {
                query = q
                presenter.searchTeam(q)
                return true
            }

            override fun onQueryTextChange(q: String): Boolean {
                query = q
                presenter.searchTeam(q)
                return false
            }
        })
        mView.swipe_team.setOnRefreshListener {
            presenter.searchTeam(query)
        }

        return mView
    }

    override fun showLoading() {
        mView.swipe_team.isRefreshing = true
    }

    override fun hideLoading() {
        mView.swipe_team.isRefreshing = false
    }

    override fun showEvent(data: List<Event>?) {}

    override fun showTeam(data: List<Team>) {
        if (data != null){
            teams.addAll(data)
        }
        adapter.notifyDataSetChanged()
    }


}
