package com.wisnusaputra.liga

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class MainActivity : AppCompatActivity() {

    private var items: MutableList<Liga> = mutableListOf()
    lateinit var activityA: MainActivityUI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initData()
        activityA = MainActivityUI()
        activityA.setContentView(this)
        activityA.recv.adapter = LigaAdapter(this, items) {
            val data : Liga = Liga(
                it.name,
                it.image,
                it.desc
            )
            startActivity<DetailActivity>("LIGA" to data)
        }
    }

    private fun initData() {
        val name = resources.getStringArray(R.array.liga_name)
        val image = resources.obtainTypedArray(R.array.liga_image)
        val desc = resources.getStringArray(R.array.liga_desc)
        items.clear()
        for (i in name.indices) {
            items.add(
                Liga(
                    name[i],
                    image.getResourceId(i, 0),
                    desc[i]
                )
            )
        }

        //Recycle the typed array
        image.recycle()
    }

    class MainActivityUI() : AnkoComponent<MainActivity> {
        lateinit var recv: RecyclerView
        override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {
            relativeLayout {
                lparams {
                    width = matchParent
                    height = matchParent
                    background = ContextCompat.getDrawable(ctx, R.color.bgapp)
                    setPadding(dip(16), 0, dip(16), 0)
                }
                recv = recyclerView {
                    layoutManager = LinearLayoutManager(ctx)
                    id = R.id.recyclev
                }.lparams {
                    width = matchParent
                    height = matchParent
                }
            }
        }

    }
}


