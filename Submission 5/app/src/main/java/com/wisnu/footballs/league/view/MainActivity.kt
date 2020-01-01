package com.wisnu.footballs.league.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.wisnu.footballs.R
import com.wisnu.footballs.favorite.view.FavoriteFragment
import com.wisnu.footballs.search.view.SearchFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.elevation = 0f
        supportActionBar?.hide()

        bottom_nav.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.league -> {
                    loadLeagueFragment()
                }
                R.id.favorite -> {
                    loadFavoriteFragment()
                }
                R.id.search -> {
                    loadSearchFragment()
                }
            }
            true
        }
        bottom_nav.selectedItemId = R.id.league
    }

    private fun loadLeagueFragment() {
        supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.container_main,
                LeagueFragment(), LeagueFragment::class.java.simpleName
            )
            .commit()
    }

    private fun loadFavoriteFragment() {
        supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.container_main,
                FavoriteFragment(),
                FavoriteFragment::class.java.simpleName
            )
            .commit()
    }

    private fun loadSearchFragment() {
        supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.container_main,
                SearchFragment(), SearchFragment::class.java.simpleName
            )
            .commit()
    }

}
