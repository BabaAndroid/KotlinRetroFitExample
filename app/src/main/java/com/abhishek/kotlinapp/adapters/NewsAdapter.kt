package com.abhishek.kotlinapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.abhishek.kotlinapp.R
import com.abhishek.kotlinapp.dataclasses.Article
import com.bumptech.glide.Glide

class NewsAdapter(val context: Context, val article: List<Article>) :
    RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {

    class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var newsimage = itemView.findViewById<ImageView>(R.id.newsImage)
        var newstitle = itemView.findViewById<TextView>(R.id.newsTitle)
        var newsdescrition = itemView.findViewById<TextView>(R.id.newsDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false)
        return ArticleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val articles = article[position]
        holder.newstitle.text = articles.title
        holder.newsdescrition.text = articles.description
        Glide.with(context)
            .load(articles.urlToImage)
            .into(holder.newsimage)
    }

    override fun getItemCount(): Int {
        return article.size
    }

}