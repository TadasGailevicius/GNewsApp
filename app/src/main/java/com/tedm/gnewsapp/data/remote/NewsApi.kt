package com.tedm.gnewsapp.data.remote

import com.tedm.gnewsapp.data.remote.entities.Articles
import com.tedm.gnewsapp.other.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("api/v4/top-headlines")
    suspend fun getTopHeadlines(
        @Query("country")
        countryCode: String = "us",
        @Query("token")
        apiKey: String = API_KEY
    ) : Response<Articles>

    @GET("api/v4/search")
    suspend fun searchForNews(
        @Query("q")
        searchQuery: String = "Obama",
        @Query("token")
        apiKey: String = API_KEY
    )  : Response<Articles>
}