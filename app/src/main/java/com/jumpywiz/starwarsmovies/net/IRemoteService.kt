package com.jumpywiz.starwarsmovies.net

import com.jumpywiz.starwarsmovies.model.CharacterRequest
import com.jumpywiz.starwarsmovies.model.MovieListRequest
import com.jumpywiz.starwarsmovies.model.PlanetRequest
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface IRemoteService {

    suspend fun getMoviesList(): Result<MovieListRequest>

    suspend fun getCharacterInfo(id: Int): Result<CharacterRequest>

}