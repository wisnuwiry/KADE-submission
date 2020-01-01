package com.wisnu.footballs.favorite.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wisnu.footballs.R
import com.wisnu.footballs.db.FavoriteDB

class FavoriteEventAdapter(
    private val events: List<FavoriteDB>,
    private val listener: (FavoriteDB) -> Unit
) :
    RecyclerView.Adapter<FavoriteEventAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val homeTeam: TextView = view.findViewById(R.id.tv_home_team)
        private val awayTeam: TextView = view.findViewById(R.id.tv_away_team)
        private val score: TextView = view.findViewById(R.id.score_team)

        fun bindItem(event: FavoriteDB, listener: (FavoriteDB) -> Unit) {
            homeTeam.text = event.homeTeam
            awayTeam.text = event.awayTeam

            var homeScore = event.homeScore
            var awayScore = event.awayScore
            if (homeScore == null) {
                homeScore = "?"
            }

            if (awayScore == null) {
                awayScore = "?"
            }
            score.text = "$homeScore : $awayScore"

            itemView.setOnClickListener {
                listener(event)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.event_item, parent, false)
        return ViewHolder(
            view
        )
    }

    override fun getItemCount(): Int = events.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bindItem(events[position], listener)

}