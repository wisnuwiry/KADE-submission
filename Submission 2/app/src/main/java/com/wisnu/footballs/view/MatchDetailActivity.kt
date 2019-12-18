package com.wisnu.footballs.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import com.wisnu.footballs.R
import com.wisnu.footballs.model.Event
import com.wisnu.footballs.model.Team
import com.wisnu.footballs.presenter.EventDetailPresenter
import com.wisnu.footballs.view.base.EventDetailView
import kotlinx.android.synthetic.main.activity_match_detail.*

class MatchDetailActivity : AppCompatActivity(), EventDetailView {

    private lateinit var presenter: EventDetailPresenter
    lateinit var event: Event

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_detail)
        val actionBar = supportActionBar

        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.elevation = 0f


        event = intent.getParcelableExtra("ID_EVENT") as Event
        presenter = EventDetailPresenter(this)
        presenter.getHomeTeam(event.idHomeTeam.toString())
        presenter.getAwayTeam(event.idAwayTeam.toString())

        var homeScore = event.homeScore
        var awayScore = event.awayScore
        if(homeScore == null){
            homeScore = "?"
        }
        if (awayScore == null){
            awayScore = "?"
        }
        tv_score.text = "$homeScore : $awayScore"
        tv_event_name.text = event.eventName
        tv_date_event.text = event.dateEvent
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    override fun dataHome(homeTeam: Team) {
        Picasso.get().load(homeTeam.teamBadge).placeholder(R.drawable.league_placeholder)
            .into(img_home_team)
        Picasso.get().load(homeTeam.teamBadge).placeholder(R.drawable.league_placeholder)
            .into(badge_home_team)
        tv_home_team.text = event.homeTeam
        home_name.text = event.homeTeam
        tv_home_score.text = event.homeScore
        tv_home_id.text = event.idHomeTeam
    }

    override fun dataAway(awayTeam: Team) {
        Picasso.get().load(awayTeam.teamBadge).placeholder(R.drawable.league_placeholder)
            .into(img_away_team)
        Picasso.get().load(awayTeam.teamBadge).placeholder(R.drawable.league_placeholder)
            .into(badge_away_team)
        tv_away_team.text = event.awayTeam
        away_name.text = event.awayTeam
        tv_away_score.text = event.awayScore
        tv_away_id.text = event.idAwayTeam
    }


    override fun onError(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
