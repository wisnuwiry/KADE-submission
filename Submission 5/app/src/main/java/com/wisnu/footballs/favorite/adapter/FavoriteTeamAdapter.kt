package com.wisnu.footballs.favorite.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.wisnu.footballs.R
import com.wisnu.footballs.db.TeamDB
import kotlinx.android.synthetic.main.team_item.view.*

class FavoriteTeamAdapter(private val teams: List<TeamDB>, private val listener: (TeamDB) -> Unit) :
    RecyclerView.Adapter<FavoriteTeamAdapter.ViewHolder>() {

    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bindItem(team: TeamDB, listener: (TeamDB) -> Unit) {
            Picasso.get().load(team.teamBadge).placeholder(R.drawable.league_placeholder)
                .into(view.tm_badge)
            view.tm_name.text = team.teamName
            view.tm_desc.text = team.teamDescription

            itemView.setOnClickListener {
                listener(team)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.team_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = teams.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bindItem(teams[position], listener)
}