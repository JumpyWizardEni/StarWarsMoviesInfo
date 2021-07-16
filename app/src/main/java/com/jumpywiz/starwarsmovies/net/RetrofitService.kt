package com.jumpywiz.starwarsmovies.net

import com.jumpywiz.starwarsmovies.model.CharacterRequest
import com.jumpywiz.starwarsmovies.model.MovieListRequest
import com.jumpywiz.starwarsmovies.model.PlanetRequest
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitService {
    @GET("films")
    suspend fun getMoviesList(): Response<MovieListRequest>

    @GET("people/{id}")
    suspend fun getCharacterInfo(@Path("id") id: Int): Response<CharacterRequest>

    @GET("planets/{id}")
    suspend fun getPlanetInfo(@Path("id") id: Int): Response<PlanetRequest>

    companion object {
        const val URL = "https://swapi.dev/api/"
    }
}