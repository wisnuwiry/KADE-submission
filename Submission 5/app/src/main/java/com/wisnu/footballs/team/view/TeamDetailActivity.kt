package com.wisnu.footballs.team.view

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.squareup.picasso.Picasso
import com.wisnu.footballs.R
import com.wisnu.footballs.db.FavoriteDB
import com.wisnu.footballs.db.TeamDB
import com.wisnu.footballs.db.database
import com.wisnu.footballs.team.adapter.TabTeamAdapter
import com.wisnu.footballs.team.model.Team
import kotlinx.android.synthetic.main.activity_team_detail.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class TeamDetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_TEAM = "extra_team"
    }

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    private lateinit var team: Team

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_detail)

        team = intent.getParcelableExtra(EXTRA_TEAM) as Team

        supportActionBar?.elevation = 0f
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = team.teamName

        Picasso.get().load(team.teamBadge).placeholder(R.drawable.league_placeholder).into(img_detail_team)
        name_detail_team.text = team.teamName
        league_team_name.text = team.teamLeague

        setFavorite()
        favoriteState()
        vp_team.adapter = TabTeamAdapter(supportFragmentManager)
        tab_team.setupWithViewPager(vp_team)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
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
                    TeamDB.TABLE_FAVORITE,
                    TeamDB.ID_TEAM to team.idTeam,
                    TeamDB.TEAM_NAME to team.teamName,
                    TeamDB.TEAM_LEAGUE to team.teamLeague,
                    TeamDB.TEAM_DESCRIPTION to team.teamDescription,
                    TeamDB.TEAM_BADGE to team.teamBadge
                )
            }
            Toast.makeText(this, "Added Team to Favorite", Toast.LENGTH_SHORT).show()
        } catch (e: SQLiteConstraintException) {
            Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun removeFavorite() {
        try {
            database.use {
                delete(
                    TeamDB.TABLE_FAVORITE, "(ID_TEAM = {id})",
                    "id" to team.idTeam.toString()
                )
            }
            Toast.makeText(this, "Remove Team from Favorite", Toast.LENGTH_SHORT).show()
        } catch (e: SQLiteConstraintException) {
            Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun setFavorite() {
        Log.d("TAG", "isFavorite: $isFavorite")
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
            val result = select(TeamDB.TABLE_FAVORITE)
                .whereArgs(
                    "(ID_TEAM = {id})",
                    "id" to team.idTeam.toString()
                )
            val favorite = result.parseList(classParser<TeamDB>())
            Log.d("TAG","State untuk Favorite : $favorite")
            if (favorite.isNotEmpty()) isFavorite = true
            setFavorite()
        }
    }
}
