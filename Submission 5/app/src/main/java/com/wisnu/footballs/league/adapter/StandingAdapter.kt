package com.wisnu.footballs.league.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wisnu.footballs.R
import com.wisnu.footballs.league.model.Standing
import kotlinx.android.synthetic.main.standing_item.*
import kotlinx.android.synthetic.main.standing_item.view.*

class StandingAdapter(
    private val standings: List<Standing>,
    private val listener: (Standing) -> Unit
) : RecyclerView.Adapter<StandingAdapter.ViewHolder>() {

    class ViewHolder(private val view: View): RecyclerView.ViewHolder(view) {
        fun bindItem(standing: Standing, position: Int, listener: (Standing) -> Unit){
            view.st_number.text = (position + 1).toString()
            view.st_team.text = standing.name
            view.st_played.text = standing.played
            view.st_win.text = standing.win
            view.st_draw.text = standing.draw
            view.st_ga_gd.text = "${standing.goalsAgainst} - ${standing.goalsDeference}"
            view.st_pts.text = standing.total
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StandingAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.standing_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = standings.size

    override fun onBindViewHolder(holder: StandingAdapter.ViewHolder, position: Int) {
        holder.bindItem(standings[position], position, listener)
    }

}