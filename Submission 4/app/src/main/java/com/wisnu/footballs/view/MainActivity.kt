package com.wisnu.footballs.view

import android.content.Intent
import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.wisnu.footballs.R
import com.wisnu.footballs.view.fragment.FavoriteFragment
import com.wisnu.footballs.view.fragment.LeagueFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottom_nav.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.league -> {
                    loadLeagueFragment(savedInstanceState)
                }
                R.id.favorite -> {
                    loadFavoriteFragment(savedInstanceState)
                }
                R.id.search -> {
                    val intent = Intent(this, SearchActivity::class.java)
                    startActivity(intent)
                }
            }
            true
        }
        bottom_nav.selectedItemId = R.id.league
    }

    private fun loadLeagueFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.container_main, LeagueFragment(), LeagueFragment::class.java.simpleName)
                .commit()
        }
    }

    private fun loadFavoriteFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(
                    R.id.container_main,
                    FavoriteFragment(),
                    FavoriteFragment::class.java.simpleName
                )
                .commit()
        }
    }
}
