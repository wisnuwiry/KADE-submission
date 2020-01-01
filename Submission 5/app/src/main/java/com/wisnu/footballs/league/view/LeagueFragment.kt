package com.wisnu.footballs.league.view


import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.gson.Gson
import com.wisnu.footballs.R
import com.wisnu.footballs.api.ApiRepository
import com.wisnu.footballs.league.model.League
import com.wisnu.footballs.league.presenter.LeaguePresenter
import com.wisnu.footballs.league.adapter.LeagueAdapter
import com.wisnu.footballs.league.interfaces.LeagueView
import kotlinx.android.synthetic.main.fragment_league.view.*

/**
 * A simple [Fragment] subclass.
 */
class LeagueFragment : Fragment(),
    LeagueView {

    private var leagues: MutableList<League> = mutableListOf()
    private lateinit var adapter: LeagueAdapter
    private lateinit var presenter: LeaguePresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_league, container, false)

        val request = ApiRepository()
        val gson = Gson()
        presenter = LeaguePresenter(
            this,
            request,
            gson
        )
        presenter.getData()

        adapter =
            LeagueAdapter(leagues) {
                val intent = Intent(activity, DetailLeagueActivity::class.java)
                intent.putExtra(DetailLeagueActivity.EXTRA_LEAGUE, it)
                startActivity(intent)
            }

        view.rv_league.adapter = adapter

        val orientation = resources.configuration.orientation
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            view.rv_league.layoutManager =
                StaggeredGridLayoutManager(3, LinearLayoutManager.VERTICAL)
        } else {
            view.rv_league.layoutManager =
                StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        }

        view.swipe.setOnRefreshListener {
            presenter.getData()
        }
        return view
    }

    override fun showLoading() {
        view?.progress_bar?.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        view?.swipe?.isRefreshing = false
        view?.progress_bar?.visibility = View.INVISIBLE
    }

    override fun showData(data: List<League>?) {
        leagues.clear()
        if (data != null) {
            leagues.addAll(data)
        }
        adapter.notifyDataSetChanged()
    }
}