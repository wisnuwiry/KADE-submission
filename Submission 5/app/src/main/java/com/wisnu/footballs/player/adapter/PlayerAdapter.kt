package com.wisnu.footballs.player.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.wisnu.footballs.R
import com.wisnu.footballs.player.model.Player
import kotlinx.android.synthetic.main.player_item.view.*

class PlayerAdapter (private val players: List<Player>, private val listener: (Player) -> Unit) :
    RecyclerView.Adapter<PlayerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.player_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = players.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bindItem(players[position], listener)

    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bindItem(player: Player, listener: (Player) -> Unit) {
            Picasso.get().load(player.avatar).placeholder(R.drawable.player_placeholder)
                .into(view.pl_avatar)

            view.pl_name.text = player.name
            view.pl_number.text = player.number
            view.pl_position.text = player.position

            itemView.setOnClickListener {
                listener(player)
            }
        }
    }
}