package com.jumpywiz.starwarsmovies.net

import com.jumpywiz.starwarsmovies.model.CharacterRequest
import com.jumpywiz.starwarsmovies.model.MovieListRequest
import retrofit2.http.GET

interface RetrofitService {
    @GET("films")
    suspend fun getMoviesList(): MovieListRequest?

    @GET("people")
    suspend fun getCharacterInfo(): CharacterRequest?

    companion object {
        const val url = "https://swapi.dev/api/"
    }
}