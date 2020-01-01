package com.wisnu.footballs.league.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.wisnu.footballs.R
import com.wisnu.footballs.league.model.League

class LeagueAdapter(private val leagues: List<League>, private val listener: (League) -> Unit) :
    RecyclerView.Adapter<LeagueAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.league_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = leagues.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bindItem(leagues[position], listener)

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val badge: ImageView = view.findViewById(R.id.img_league)
        private val name: TextView = view.findViewById(R.id.tv_name_league)
        private val description: TextView = view.findViewById(R.id.tv_desc_league)
        fun bindItem(league: League, listener: (League) -> Unit) {
            Picasso.get().load(league.leagueBadge).placeholder(R.drawable.league_placeholder)
                .into(badge)
            name.text = league.leagueName
            description.text = league.leagueDesc

            itemView.setOnClickListener {
                listener(league)
            }
        }
    }
}


