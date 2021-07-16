package com.jumpywiz.starwarsmovies.net

import com.jumpywiz.starwarsmovies.model.CharacterRequest
import com.jumpywiz.starwarsmovies.model.MovieListRequest
import com.jumpywiz.starwarsmovies.model.PlanetRequest

interface IRemoteService {

    suspend fun getMoviesList(): Result<MovieListRequest>

    suspend fun getCharacterInfo(id: Int): Result<CharacterRequest>

    suspend fun getPlanetInfo(id: Int): Result<PlanetRequest>
}