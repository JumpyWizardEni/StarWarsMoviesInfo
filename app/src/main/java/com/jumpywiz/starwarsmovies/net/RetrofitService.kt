package com.jumpywiz.starwarsmovies.net

import com.jumpywiz.starwarsmovies.model.MovieRequest
import retrofit2.http.GET

interface RetrofitService {
    @GET("films")
    suspend fun getMoviesList(): MovieRequest?

    companion object {
        val url = "https://swapi.dev/api/"
    }
}