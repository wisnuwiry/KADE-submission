package com.wisnu.footballs.league.view

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import com.wisnu.footballs.R
import com.wisnu.footballs.league.model.League
import com.wisnu.footballs.league.adapter.TabLeagueAdapter
import kotlinx.android.synthetic.main.activity_detail_league.*

class DetailLeagueActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_LEAGUE = "extra_league"
    }

    private lateinit var imgLeague: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_league)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.elevation = 0f

        imgLeague = findViewById(R.id.img_detail_league)
        val league = intent.getParcelableExtra(EXTRA_LEAGUE) as League

        supportActionBar?.title = league.leagueName
        Picasso.get().load(league.leagueBadge).placeholder(R.drawable.league_placeholder)
            .into(imgLeague)
        tv_detail_name.text = league.leagueName

        vp_league.adapter = TabLeagueAdapter(supportFragmentManager)
        tab_league.setupWithViewPager(vp_league)

    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
