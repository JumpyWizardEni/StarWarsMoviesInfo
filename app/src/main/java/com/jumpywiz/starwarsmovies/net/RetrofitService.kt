package com.jumpywiz.starwarsmovies.net

import CharacterRequest
import com.jumpywiz.starwarsmovies.model.MovieListRequest
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitService {
    @GET("films")
    suspend fun getMoviesList(): MovieListRequest?

    @GET("people/{characterId}")
    suspend fun getCharacterInfo(@Path("characterId") characterId: Int): CharacterRequest?

    companion object {
        val url = "https://swapi.dev/api/"
    }
}