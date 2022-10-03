package com.example.moviescomposeapp.rest

import com.example.moviescomposeapp.model.MoviesItem
import retrofit2.Response
import retrofit2.http.GET

interface MoviesApi {

    @GET("movielist.json")
    suspend fun getMovies(): Response<List<MoviesItem>>

    companion object {
        const val BASE_URL = "https://howtodoandroid.com/apis/"
    }
}