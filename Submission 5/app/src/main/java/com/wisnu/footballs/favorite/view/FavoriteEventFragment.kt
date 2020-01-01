package com.wisnu.footballs.favorite.view


import android.content.Intent
import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager

import com.wisnu.footballs.R
import com.wisnu.footballs.db.FavoriteDB
import com.wisnu.footballs.db.database
import com.wisnu.footballs.event.model.Event
import com.wisnu.footballs.event.view.MatchDetailActivity
import com.wisnu.footballs.favorite.adapter.FavoriteEventAdapter
import kotlinx.android.synthetic.main.fragment_favorite_event.view.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

/**
 * A simple [Fragment] subclass.
 */
class FavoriteEventFragment : Fragment() {

    private val favoriteEvent: MutableList<FavoriteDB> = mutableListOf()
    private lateinit var eventAdapter: FavoriteEventAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_favorite_event, container, false)

        eventAdapter =
            FavoriteEventAdapter(favoriteEvent) {
                val intent = Intent(activity, MatchDetailActivity::class.java)
                val event = Event(
                    it.eventId,
                    it.eventName,
                    it.homeTeam,
                    it.awayTeam,
                    it.homeScore,
                    it.awayScore,
                    it.dateEvent,
                    it.idHomeTeam,
                    it.idAwayTeam
                )
                intent.putExtra("ID_EVENT", event)
                startActivity(intent)
            }

        view.rv_favorite_event.layoutManager = LinearLayoutManager(activity)
        view.rv_favorite_event.adapter = eventAdapter

        showDataFavorite()

        view.swipe.setOnRefreshListener {
            favoriteEvent.clear()
            showDataFavorite()
            view.swipe.isRefreshing = false
        }

        return view
    }

    private fun showDataFavorite() {
        try {
            context?.database?.use {
                val result = select(FavoriteDB.TABLE_FAVORITE)
                val data = result.parseList(classParser<FavoriteDB>())
                favoriteEvent.addAll(data)
                eventAdapter.notifyDataSetChanged()
            }
        } catch (e: SQLiteConstraintException) {
            Toast.makeText(activity, e.message, Toast.LENGTH_SHORT).show()
        }
    }
}
