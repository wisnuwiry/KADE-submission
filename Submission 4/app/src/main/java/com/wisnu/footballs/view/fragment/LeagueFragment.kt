package com.wisnu.footballs.view.fragment


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
import com.wisnu.footballs.model.League
import com.wisnu.footballs.presenter.LeaguePresenter
import com.wisnu.footballs.view.DetailLeagueActivity
import com.wisnu.footballs.view.adapter.LeagueAdapter
import com.wisnu.footballs.view.base.LeagueView
import kotlinx.android.synthetic.main.fragment_league.view.*

/**
 * A simple [Fragment] subclass.
 */
class LeagueFragment : Fragment(), LeagueView {

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
        presenter = LeaguePresenter(this, request, gson)
        presenter.getData()

        adapter = LeagueAdapter(leagues) {
            val intent = Intent(activity, DetailLeagueActivity::class.java)
            intent.putExtra(DetailLeagueActivity.EXTRA_LEAGUE, it)
            startActivity(intent)
        }

        view.rv_league.adapter = adapter
        view.rv_league.layoutManager = LinearLayoutManager(activity)

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