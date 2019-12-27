package com.wisnu.footballs.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.wisnu.footballs.R
import com.wisnu.footballs.api.ApiRepository
import com.wisnu.footballs.model.Event
import com.wisnu.footballs.presenter.EventPresenter
import com.wisnu.footballs.view.adapter.EventAdapter
import com.wisnu.footballs.view.base.EventView
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity(), EventView {

    private val events: MutableList<Event> = mutableListOf()
    private lateinit var adapter: EventAdapter
    private lateinit var presenter: EventPresenter
    private var query: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Search Event"

        val request = ApiRepository()
        val gson = Gson()
        presenter = EventPresenter(this, request, gson)

        adapter = EventAdapter(events) {
            val intent = Intent(this, MatchDetailActivity::class.java)
            intent.putExtra("ID_EVENT", it)
            startActivity(intent)
        }

        progress.visibility = View.INVISIBLE

        rv_event.layoutManager = LinearLayoutManager(this)
        rv_event.adapter = adapter

        swipe.setOnRefreshListener {
            swipe.isRefreshing = false
            presenter.search(query)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_bar, menu)
        val searchItem = menu?.findItem(R.id.search_action)
        var searchView: SearchView? = null
        if (searchItem != null) {
            searchView = searchItem.actionView as SearchView
        }
        searchView?.isSubmitButtonEnabled
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(q: String): Boolean {
                query = q
                presenter.search(q)
                return true
            }

            override fun onQueryTextChange(q: String): Boolean {
                query = q
                presenter.search(q)
                return false
            }
        })

        return true
    }

    override fun showLoading() {
        progress.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progress.visibility = View.INVISIBLE
    }

    override fun showData(data: List<Event>?) {
        print(data)
        if (data != null) {
            events.clear()
            events.addAll(data)
        }
        adapter.notifyDataSetChanged()
    }
}
