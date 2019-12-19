package com.wisnu.footballs.view.fragment


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.wisnu.footballs.R
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
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_league, container, false)

        presenter = LeaguePresenter(this)
        presenter.getData()

        adapter = LeagueAdapter(leagues) {
            val data = League(
                it.leagueId,
                it.leagueName,
                it.leagueDesc,
                it.leagueBadge
            )
            val intent = Intent(activity, DetailLeagueActivity::class.java)
            intent.putExtra(DetailLeagueActivity.EXTRA_LEAGUE, data)
            startActivity(intent)
        }

        view.rv_league.adapter = adapter
        view.rv_league.layoutManager = LinearLayoutManager(activity)

        view.swipe.setOnRefreshListener {
            onSwipe()
        }
        return view
    }

    private fun onSwipe() {
        view?.progress_bar?.visibility = View.VISIBLE
        view?.swipe?.isRefreshing = false
        presenter.getData()
    }

    override fun onSuccess(data: List<League>) {
        leagues.addAll(data)
        adapter.notifyDataSetChanged()
        view?.progress_bar?.visibility = View.INVISIBLE
    }

    override fun onError(message: String?) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }


}
