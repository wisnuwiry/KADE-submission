package com.wisnu.footballs.favorite.view


import android.content.Intent
import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.wisnu.footballs.R
import com.wisnu.footballs.db.TeamDB
import com.wisnu.footballs.db.database
import com.wisnu.footballs.favorite.adapter.FavoriteTeamAdapter
import com.wisnu.footballs.team.model.Team
import com.wisnu.footballs.team.view.TeamDetailActivity
import kotlinx.android.synthetic.main.fragment_favorite_team.view.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

/**
 * A simple [Fragment] subclass.
 */
class FavoriteTeamFragment : Fragment() {

    private val favoriteTeam: MutableList<TeamDB> = mutableListOf()
    private lateinit var teamAdapter: FavoriteTeamAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val mView = inflater.inflate(R.layout.fragment_favorite_team, container, false)

        teamAdapter = FavoriteTeamAdapter(favoriteTeam) {
            val intent = Intent(activity, TeamDetailActivity::class.java)
            val team = Team(
                it.idTeam,
                it.teamName,
                it.teamLeague,
                it.teamDescription,
                it.teamBadge,
                "Soccer"
            )
            intent.putExtra(TeamDetailActivity.EXTRA_TEAM, team)
            startActivity(intent)
        }

        mView.rv_favorite_team.layoutManager = LinearLayoutManager(activity)
        mView.rv_favorite_team.adapter = teamAdapter

        showDataFavorite()

        mView.swipe.setOnRefreshListener {
            favoriteTeam.clear()
            showDataFavorite()
            mView.swipe.isRefreshing = false
        }
        return mView
    }

    private fun showDataFavorite() {
        try {
            context?.database?.use {
                val result = select(TeamDB.TABLE_FAVORITE)
                val data = result.parseList(classParser<TeamDB>())
                favoriteTeam.addAll(data)
                teamAdapter.notifyDataSetChanged()
            }
        } catch (e: SQLiteConstraintException) {
            Toast.makeText(activity, e.message, Toast.LENGTH_SHORT).show()
        }
    }
}
