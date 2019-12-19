package com.wisnu.footballs.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import com.wisnu.footballs.R
import com.wisnu.footballs.model.League
import com.wisnu.footballs.view.adapter.TabAdapter
import kotlinx.android.synthetic.main.activity_match.*

class MatchActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_LEAGUE = "extra_league"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val league = intent.getParcelableExtra(EXTRA_LEAGUE) as League
        supportActionBar?.title = league.leagueName
        viewpager.adapter = TabAdapter(supportFragmentManager)
        tab.setupWithViewPager(viewpager)
        Picasso.get().load(league.leagueBadge).placeholder(R.drawable.league_placeholder)
            .into(badge)
        name.text = league.leagueName
        desc.text = league.leagueDesc
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_manu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.go_to_search){
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }

}
