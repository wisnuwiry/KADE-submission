package com.wisnusaputra.liga

import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import org.jetbrains.anko.*

class LigaItem : AnkoComponent<ViewGroup> {

    override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui) {
        linearLayout {
            lparams {
                width = matchParent
                height = wrapContent
                margin = dip(8)
                padding = dip(16)
                background = ContextCompat.getDrawable(ctx, R.color.white)
            }

            imageView {
                id = R.id.liga_img
            }.lparams {
                width = dip(80)
                height = dip(80)
                setMargins(0, 0, dip(16), 0)
            }

            textView {
                id = R.id.liga_name
                textSize = dip(16).toFloat()
            }
        }
    }

}