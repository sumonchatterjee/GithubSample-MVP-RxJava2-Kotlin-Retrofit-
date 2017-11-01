package com.example.test.sample

import android.arch.lifecycle.LifecycleOwner
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import greenRobotEvents.SearchEvents
import kotlinx.android.synthetic.main.fragment_search.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import kotlinx.android.synthetic.main.fragment_search.view.*

/**
 * Created by sumon.chatterjee on 01/11/17.
 */
class SearchFragment : Fragment(), LifecycleOwner {

    lateinit var mProgressBar: ProgressBar
    var searchPresenter = SearchPresenter()
    lateinit var mAdapter:SearchAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mAdapter = SearchAdapter(context)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_search, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        searchPresenter.getSearchResult();
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mRecyclerView!!.layoutManager = LinearLayoutManager(activity)
        mRecyclerView!!.adapter = mAdapter
    }

    @Subscribe
    fun onEvent(event: SearchEvents.SearchResultEvent) {
        val result = event.data
        val array = result!!.getAsJsonArray("items")

        mAdapter.setJsonArray(array)
        mAdapter.notifyDataSetChanged()

    }


    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)

    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe
    fun showProgress(event: SearchEvents.SearchShowProgress) {
        mProgressBar!!.visibility = View.VISIBLE
    }

    @Subscribe
    fun hideProgress(event: SearchEvents.SearchHideProgress) {
        mProgressBar!!.visibility = View.GONE
    }

}