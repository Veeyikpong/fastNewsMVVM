package com.veeyikpong.fastnews.ui.searchnews.search.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.veeyikpong.fastnews.R
import com.veeyikpong.fastnews.api.response.News

class NewsAdapter(val context: Context, var newsList: List<News>): RecyclerView.Adapter<NewsViewHolder>() {

    interface OnItemClickListener{
        fun onItemClick(news: News)
    }

    private lateinit var onItemClickListener: OnItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val v = NewsViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_news,
                parent,
                false
            )
        )
        v.itemView.setOnClickListener {
            if(::onItemClickListener.isInitialized){
                onItemClickListener.onItemClick(newsList[v.adapterPosition])
            }
        }
        return v
    }

    override fun getItemCount(): Int {
        if(newsList == null)
            return 0

        return newsList.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val post = newsList[position]
        holder.setTitle(post.title)
        holder.setBody(post.body)
    }

    fun setList(newsList: List<News>){
        this.newsList = newsList
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(clickListener: OnItemClickListener){
        this.onItemClickListener = clickListener
    }
}