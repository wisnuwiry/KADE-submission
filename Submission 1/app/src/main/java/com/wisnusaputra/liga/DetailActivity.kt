package com.wisnusaputra.liga

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import org.jetbrains.anko.*

class DetailActivity : AppCompatActivity(), AnkoLogger {

    private lateinit var activityB: DetailActivityUI

    private fun isi() {
        val img = find<ImageView>(R.id.d_img)
        val name = find<TextView>(R.id.d_name)
        val desc = find<TextView>(R.id.d_desc)

        val i = intent.extras
        val data = i.getParcelable<Liga>("LIGA")

        data.image?.let { img.setImageResource(it) }
        name.text = data.name
        desc.text = data.desc
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        activityB = DetailActivityUI()
        activityB.setContentView(this)
        isi()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    class DetailActivityUI() : AnkoComponent<DetailActivity> {
        override fun createView(ui: AnkoContext<DetailActivity>) = with(ui) {
            linearLayout {
                orientation = LinearLayout.VERTICAL
                gravity = Gravity.CENTER_HORIZONTAL
                padding = dip(20)
                background = ContextCompat.getDrawable(ctx, R.color.bgapp)
                imageView {
                    imageResource = R.drawable.american
                    id = R.id.d_img
                    backgroundColor = Color.WHITE
                    padding = dip(20)
                    scaleType = ImageView.ScaleType.FIT_CENTER
                }.lparams(width = matchParent, height = dip(300))
                linearLayout {
                    background = ContextCompat.getDrawable(ctx, R.color.white)
                    orientation = LinearLayout.VERTICAL
                    padding = dip(10)

                    textView {
                        id = R.id.d_name
                        textSize = 30f
                        setTypeface(typeface, Typeface.BOLD)
                    }.lparams {
                        topMargin = dip(10)
                        bottomMargin = dip(20)
                    }
                    textView {
                        textSize = 20f
                        id = R.id.d_desc
                    }
                }.lparams(width = matchParent) {
                    topMargin = dip(15)
                }
            }
        }

    }

}