package com.wisnu.footballs.view.fragment


import android.content.Intent
import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.wisnu.footballs.R
import com.wisnu.footballs.db.Favorite
import com.wisnu.footballs.db.database
import com.wisnu.footballs.model.Event
import com.wisnu.footballs.view.MatchDetailActivity
import com.wisnu.footballs.view.adapter.FavoriteAdapter
import kotlinx.android.synthetic.main.fragment_favorite.view.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

/**
 * A simple [Fragment] subclass.
 */
class FavoriteFragment : Fragment() {

    private val favoriteEvent: MutableList<Favorite> = mutableListOf()
    private lateinit var adapter: FavoriteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_favorite, container, false)

        adapter = FavoriteAdapter(favoriteEvent) {
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

        view.rv_favorite.layoutManager = LinearLayoutManager(activity)
        view.rv_favorite.adapter = adapter

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
                val result = select(Favorite.TABLE_FAVORITE)
                val data = result.parseList(classParser<Favorite>())
                favoriteEvent.addAll(data)
                adapter.notifyDataSetChanged()
            }
        } catch (e: SQLiteConstraintException) {
            Toast.makeText(activity, e.message, Toast.LENGTH_SHORT).show()
        }
    }
}
