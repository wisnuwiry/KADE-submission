package com.wisnu.footballs.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.wisnu.footballs.R
import com.wisnu.footballs.view.adapter.LeagueAdapter
import com.wisnu.footballs.view.base.LeagueView
import com.wisnu.footballs.model.League
import com.wisnu.footballs.presenter.LeaguePresenter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), LeagueView {

    private var leagues: MutableList<League> = mutableListOf()
    private lateinit var adapter: LeagueAdapter
    private lateinit var presenter: LeaguePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = LeaguePresenter(this)
        presenter.getData()

        adapter = LeagueAdapter(leagues) {
            val data = League(
                it.leagueId,
                it.leagueName,
                it.leagueDesc,
                it.leagueBadge
            )
            val intent = Intent(this, DetailLeagueActivity::class.java)
            intent.putExtra(DetailLeagueActivity.EXTRA_LEAGUE, data)
            startActivity(intent)
        }

        rv_league.adapter = adapter
        rv_league.layoutManager = LinearLayoutManager(this)

        swipe.setOnRefreshListener {
            onSwipe()
        }
    }

    private fun onSwipe() {
        progress_bar.visibility = View.VISIBLE
        swipe.isRefreshing = false
        presenter.getData()
    }

    override fun onSuccess(data: List<League>) {
        leagues.addAll(data)
        adapter.notifyDataSetChanged()
        progress_bar.visibility = View.INVISIBLE
    }

    override fun onError(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
