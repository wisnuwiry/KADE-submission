package com.wisnu.footballs.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wisnu.footballs.R
import com.wisnu.footballs.model.Event

class EventAdapter(private val events: List<Event>, private val listener: (Event) -> Unit) :
    RecyclerView.Adapter<EventAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val nameEvent: TextView = view.findViewById(R.id.event_name)
        private val homeTeam: TextView = view.findViewById(R.id.tv_home_team)
        private val awayTeam: TextView = view.findViewById(R.id.tv_away_team)
        private val dateEvent: TextView = view.findViewById(R.id.event_date)
        private val score: TextView = view.findViewById(R.id.score_team)

        fun bindItem(event: Event, listener: (Event) -> Unit) {
            nameEvent.text = event.eventName
            homeTeam.text = event.homeTeam
            awayTeam.text = event.awayTeam
            dateEvent.text = event.dateEvent

            var homeScore = event.homeScore
            var awayScore = event.awayScore
            if (homeScore == null){
                homeScore = "?"
            }

            if (awayScore == null){
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
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = events.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bindItem(events[position], listener)

}