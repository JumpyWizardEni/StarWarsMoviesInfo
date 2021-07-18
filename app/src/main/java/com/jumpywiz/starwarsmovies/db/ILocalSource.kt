package com.jumpywiz.starwarsmovies.db

import com.jumpywiz.starwarsmovies.model.CharacterDB
import com.jumpywiz.starwarsmovies.model.MovieDB
import com.jumpywiz.starwarsmovies.model.PlanetDB

interface ILocalSource {

    suspend fun getAllMovies(): List<MovieDB>

    suspend fun getMovie(id: Int): List<MovieDB>

    suspend fun getCharacter(url: String): List<CharacterDB>

    suspend fun getPlanet(url: String): List<PlanetDB>

    suspend fun setAllMovies(movies: List<MovieDB>)

    suspend fun setCharacter(character: CharacterDB)

    suspend fun setPlanet(planet: PlanetDB)
}