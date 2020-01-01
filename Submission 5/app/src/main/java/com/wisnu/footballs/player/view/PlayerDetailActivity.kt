package com.wisnu.footballs.player.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import com.wisnu.footballs.R
import com.wisnu.footballs.player.model.Player
import kotlinx.android.synthetic.main.activity_player_detail.*

class PlayerDetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_PLAYER = "extra_player"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_detail)

        val player = intent.getParcelableExtra(EXTRA_PLAYER) as Player

        supportActionBar?.elevation = 0f
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = player.name

        Picasso.get().load(player.avatar).placeholder(R.drawable.player_placeholder)
            .into(player_avatar)
        player_name.text = getString(player.name)
        player_name_1.text = getString(player.name)
        player_born.text = getString(player.date)
        player_number.text = getString(player.number)
        player_position.text = getString(player.position)
        player_height.text = getString(player.height)
        player_description.text = getString(player.desc)

    }

    private fun getString(value: String?): String {
        if (value == null || value?.isEmpty()) {
            return "-"
        } else {
            return value
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}
