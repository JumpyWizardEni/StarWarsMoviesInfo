package com.jumpywiz.starwarsmovies.net

import com.jumpywiz.starwarsmovies.model.CharacterRequest
import com.jumpywiz.starwarsmovies.model.MovieListRequest
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitService {
    @GET("films")
    suspend fun getMoviesList(): MovieListRequest?

    @GET("people/{id}")
    suspend fun getCharacterInfo(@Path("id") id: Int): CharacterRequest?

    companion object {
        const val url = "https://swapi.dev/api/"
    }
}