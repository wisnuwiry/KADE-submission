package com.wisnu.footballs.team.view


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson

import com.wisnu.footballs.R
import com.wisnu.footballs.api.ApiRepository
import com.wisnu.footballs.league.model.League
import com.wisnu.footballs.team.adapter.TeamAdapter
import com.wisnu.footballs.team.interfaces.TeamView
import com.wisnu.footballs.team.model.Team
import com.wisnu.footballs.team.presenter.TeamPresenter
import kotlinx.android.synthetic.main.fragment_team.view.*

/**
 * A simple [Fragment] subclass.
 */
class TeamFragment : Fragment(), TeamView {

    private var teams: MutableList<Team> = mutableListOf()
    private lateinit var adapter: TeamAdapter
    private lateinit var presenter: TeamPresenter

    companion object{
        const val EXTRA_LEAGUE = "extra_league"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_team, container, false)

        val league = activity?.intent?.getParcelableExtra(EXTRA_LEAGUE) as League

        val request = ApiRepository()
        val gson = Gson()

        presenter = TeamPresenter(this, request, gson)
        presenter.getData(league.leagueId)

        adapter = TeamAdapter(teams){
            val intent = Intent(activity, TeamDetailActivity::class.java)
            intent.putExtra(TeamDetailActivity.EXTRA_TEAM, it)
            startActivity(intent)
        }
        view.rv_team.adapter = adapter
        view.rv_team.layoutManager = LinearLayoutManager(activity)

        view.swipe.setOnRefreshListener{
            presenter.getData(league.leagueId)
            view.swipe.isRefreshing = false
        }
        return view
    }

    override fun showLoading() {
        view?.progress?.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        view?.progress?.visibility = View.INVISIBLE
    }

    override fun showData(data: List<Team>?) {
        teams.clear()
        print("Team: $teams")
        if(data != null){
            teams.addAll(data)
        }
        adapter.notifyDataSetChanged()
    }
}
