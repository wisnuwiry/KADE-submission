package com.wisnu.footballs.view.fragment


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.wisnu.footballs.R
import com.wisnu.footballs.model.Event
import com.wisnu.footballs.model.League
import com.wisnu.footballs.presenter.EventPresenter
import com.wisnu.footballs.view.MatchActivity
import com.wisnu.footballs.view.MatchDetailActivity
import com.wisnu.footballs.view.adapter.EventAdapter
import com.wisnu.footballs.view.base.EventView
import kotlinx.android.synthetic.main.fragment_prev_match.view.*

/**
 * A simple [Fragment] subclass.
 */
class PrevMatchFragment : Fragment(), EventView {

    private val events: MutableList<Event> = mutableListOf()
    private lateinit var adapter: EventAdapter
    private lateinit var presenter: EventPresenter
    private lateinit var progress: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_prev_match, container, false)
        progress = view.findViewById(R.id.progress)

        val league = activity?.intent?.getParcelableExtra(MatchActivity.EXTRA_LEAGUE) as League
        presenter = EventPresenter(this)
        presenter.getPrev(league.leagueId.toString())

        adapter = EventAdapter(events) {
            val intent = Intent(activity, MatchDetailActivity::class.java)
            intent.putExtra("ID_EVENT", it)
            startActivity(intent)
        }

        view.rv_event_prev.adapter = adapter
        view.rv_event_prev.layoutManager = LinearLayoutManager(context)

        view.swipe.setOnRefreshListener {
            view.swipe.isRefreshing = false
            presenter.getPrev(league.leagueId.toString())
        }
        return view
    }

    override fun onSuccess(data: List<Event>) {
        progress.visibility = View.INVISIBLE
        events.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun onError(message: String?) {
        progress.visibility = View.INVISIBLE
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }
}
