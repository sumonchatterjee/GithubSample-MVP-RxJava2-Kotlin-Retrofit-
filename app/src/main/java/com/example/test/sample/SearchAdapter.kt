package com.example.test.sample

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.google.gson.JsonArray
import kotlinx.android.synthetic.main.search_item.view.*



/**
 * Created by sumon.chatterjee on 01/11/17.
 */
class SearchAdapter(context: Context) : RecyclerView.Adapter<SearchAdapter.SearchHolder>() {

    var mLayoutInflater: LayoutInflater
    var mContext:Context

    private var mDataArray: JsonArray? = null

    init {
        mContext=context
        mDataArray = JsonArray()
        mLayoutInflater = LayoutInflater.from(mContext)
    }

    fun setJsonArray(jsonArray: JsonArray) {
        this.mDataArray = jsonArray
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): SearchHolder {
        return SearchHolder(mLayoutInflater.inflate(R.layout.search_item, parent, false))
    }

    override fun onBindViewHolder(holder: SearchHolder?, position: Int) {
        holder!!.onBind(position)
    }

    override fun getItemCount(): Int {
        return mDataArray!!.size()
    }

    inner class SearchHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(position: Int) {
            val data = mDataArray!!.get(position).asJsonObject

            val photoUrl = data!!.get("avatar_url")

            Glide.with(mContext)
                    .load(photoUrl)
                    .into(itemView.avatar_imgvw)

            itemView.user.text=((data.get("login").asString))
            itemView.user_id.text=((data.get("type").asString))
        }
    }
}