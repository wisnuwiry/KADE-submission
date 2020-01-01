package com.wisnu.footballs.team.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.wisnu.footballs.R
import com.wisnu.footballs.team.model.Team
import kotlinx.android.synthetic.main.team_item.view.*

class TeamAdapter(private val teams: List<Team>, private val listener: (Team) -> Unit) :
    RecyclerView.Adapter<TeamAdapter.ViewHolder>() {

    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bindItem(team: Team, listener: (Team) -> Unit) {
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