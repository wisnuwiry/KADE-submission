package com.wisnu.footballs.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.squareup.picasso.Picasso
import com.wisnu.footballs.R
import com.wisnu.footballs.model.League
import kotlinx.android.synthetic.main.activity_detail_league.*

class DetailLeagueActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_LEAGUE = "extra_league"
    }

    private lateinit var imgLeague: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_league)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        imgLeague = findViewById(R.id.img_detail_league)
        val league = intent.getParcelableExtra(EXTRA_LEAGUE) as League

        supportActionBar?.title = league.leagueName
        Picasso.get().load(league.leagueBadge).placeholder(R.drawable.league_placeholder).into(imgLeague)
        tv_detail_name.text = league.leagueName

        tv_detail_desc.text = league.leagueDesc

        go_to_match.setOnClickListener {
            val intent = Intent(this, MatchActivity::class.java)
            intent.putExtra(MatchActivity.EXTRA_LEAGUE, league)
            startActivity(intent)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
