package com.wisnu.footballs.view

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import com.wisnu.footballs.R
import com.wisnu.footballs.api.ApiRepository
import com.wisnu.footballs.db.Favorite
import com.wisnu.footballs.db.database
import com.wisnu.footballs.model.Event
import com.wisnu.footballs.model.Team
import com.wisnu.footballs.presenter.EventDetailPresenter
import com.wisnu.footballs.view.base.EventDetailView
import kotlinx.android.synthetic.main.activity_match_detail.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class MatchDetailActivity : AppCompatActivity(), EventDetailView {

    private lateinit var presenter: EventDetailPresenter
    lateinit var event: Event
    private lateinit var eventId: String
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_detail)
        val actionBar = supportActionBar

        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.elevation = 0f

        event = intent.getParcelableExtra("ID_EVENT") as Event
        eventId = event.eventId.toString()

        favoriteState()

        val request = ApiRepository()
        val gson = Gson()

        presenter = EventDetailPresenter(this, request, gson)
        presenter.getHomeTeam(event.idHomeTeam.toString())
        presenter.getAwayTeam(event.idAwayTeam.toString())

        var homeScore = event.homeScore
        var awayScore = event.awayScore
        if (homeScore == null) {
            homeScore = "?"
        }
        if (awayScore == null) {
            awayScore = "?"
        }
        tv_score.text = "$homeScore : $awayScore"
        tv_event_name.text = event.eventName
        tv_date_event.text = event.dateEvent

        swipe.setOnRefreshListener {
            presenter.getHomeTeam(event.idHomeTeam.toString())
            presenter.getAwayTeam(event.idAwayTeam.toString())
        }
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

    override fun showLoading() {
        progress.visibility = View.VISIBLE
    }


    override fun hideLoading() {
        progress.visibility = View.INVISIBLE
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.favorite, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.add_to_favorite -> {
                if (isFavorite) removeFavorite() else addFavorite()

                isFavorite = !isFavorite
                setFavorite()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun addFavorite() {
        try {
            database.use {
                insert(
                    Favorite.TABLE_FAVORITE,
                    Favorite.EVENT_ID to event.eventId,
                    Favorite.EVENT_NAME to event.eventName,
                    Favorite.HOME_TEAM to event.homeTeam,
                    Favorite.AWAY_TEAM to event.awayTeam,
                    Favorite.HOME_SCORE to event.homeScore,
                    Favorite.AWAY_SCORE to event.awayScore,
                    Favorite.DATE_EVENT to event.dateEvent,
                    Favorite.ID_HOME_TEAM to event.idHomeTeam,
                    Favorite.ID_AWAY_TEAM to event.idAwayTeam
                )
            }
            Toast.makeText(this, "Added Event to Favorite", Toast.LENGTH_SHORT).show()
        } catch (e: SQLiteConstraintException) {
            Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun removeFavorite() {
        try {
            database.use {
                delete(
                    Favorite.TABLE_FAVORITE, "(EVENT_ID = {id})",
                    "id" to event.eventId.toString()
                )
            }
            Toast.makeText(this, "Remove Event from Favorite", Toast.LENGTH_SHORT).show()
        } catch (e: SQLiteConstraintException) {
            Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun setFavorite() {
        if (!isFavorite) {
            menuItem?.getItem(0)?.icon =
                ContextCompat.getDrawable(this, R.drawable.ic_favorite_border)
        } else {
            menuItem?.getItem(0)?.icon =
                ContextCompat.getDrawable(this, R.drawable.ic_favorite)
        }
    }

    private fun favoriteState() {
        database.use {
            val result = select(Favorite.TABLE_FAVORITE)
                .whereArgs(
                    "( EVENT_ID = {id_event})",
                    "id_event" to eventId
                )
            val favorite = result.parseList(classParser<Favorite>())
            if (favorite.isNotEmpty()) isFavorite = true
            setFavorite()
        }
    }
}
