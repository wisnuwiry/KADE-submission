package com.wisnusaputra.liga

import android.content.Context
import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity

class LigaAdapter(
    private val context: Context,
    private val items: List<Liga>,
    private val listener: (Liga) -> Unit
) :
    RecyclerView.Adapter<LigaAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LigaItem().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position], listener)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val ligaName = view.find<TextView>(R.id.liga_name)
        private val ligaImg = view.find<ImageView>(R.id.liga_img)
        fun bindItem(items: Liga, listener: (Liga) -> Unit) {
            ligaName.text = items.name
            items.image?.let { Picasso.get().load(it).fit().into(ligaImg) }

            itemView.setOnClickListener {
                listener(items)
            }
        }
    }

}