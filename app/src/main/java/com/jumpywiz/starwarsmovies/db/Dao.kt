package com.jumpywiz.starwarsmovies.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.jumpywiz.starwarsmovies.model.*

@Dao
interface Dao {
    @Query("SELECT * FROM moviedb")
    suspend fun getAllMovies(): List<MovieDB>

    @Query("SELECT * FROM moviedb where episodeId = :id")
    suspend fun getMovie(id: Int) : List<MovieDB>

    @Query("SELECT * FROM characterdb where url = :url")
    suspend fun getCharacter(url: String): List<CharacterDB>

    @Query("SELECT * FROM planetdb where url = :url")
    suspend fun getPlanet(url: String): List<PlanetDB>

    @Insert
    suspend fun setAllMovies(movies: List<MovieDB>)

    @Insert
    suspend fun setCharacter(character: CharacterDB)

    @Insert
    suspend fun setPlanet(planet: PlanetDB)
}