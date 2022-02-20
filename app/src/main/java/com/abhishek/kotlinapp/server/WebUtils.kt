package com.abhishek.kotlinapp.server


import com.abhishek.kotlinapp.dataclasses.News
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL = "https://newsapi.org/";
const val API_KEY = "e23eb52f8710467d9028d335eaceda78";

//creating api endpoints
interface NewsInterface {

    // TODO: API for getting Top Articles
    @GET("v2/top-headlines?apiKey=$API_KEY")
    fun getHeadlines(@Query("country") country: String, @Query("page") page: Int): Call<News>

}

//creating retrofit instance using bject class
object NewsService {
    val newsInstance: NewsInterface

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        newsInstance = retrofit.create(NewsInterface::class.java)
    }
}