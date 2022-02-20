package com.abhishek.kotlinapp

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abhishek.kotlinapp.adapters.NewsAdapter
import com.abhishek.kotlinapp.dataclasses.News
import com.abhishek.kotlinapp.server.NewsService
import com.facebook.shimmer.ShimmerFrameLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SplashScreenActivity : AppCompatActivity() {

    lateinit var adapter: NewsAdapter
    lateinit var newsList: RecyclerView
    lateinit var shimmerlayout: ShimmerFrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        //init ui views
        newsList = findViewById(R.id.newsList)
        shimmerlayout = findViewById(R.id.shimmer_layout)
        shimmerlayout.startShimmer()

        //getting news from server
        getNews()
    }

    private fun getNews() {
        val news = NewsService.newsInstance.getHeadlines("in", 1)
        news.enqueue(object : Callback<News> {
            override fun onResponse(call: Call<News>, response: Response<News>) {
                val news = response.body()
                if (news != null) {
                    Log.d("ResponceNews", news.toString())

                    //init nwes adapter
                    newsList.visibility = View.VISIBLE
                    shimmerlayout.stopShimmer()
                    shimmerlayout.visibility = View.GONE
                    adapter = NewsAdapter(this@SplashScreenActivity, news.articles)
                    newsList.adapter = adapter
                    newsList.layoutManager = LinearLayoutManager(this@SplashScreenActivity)

                }
            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

}

