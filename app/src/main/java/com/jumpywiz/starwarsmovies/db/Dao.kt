package com.jumpywiz.starwarsmovies.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.jumpywiz.starwarsmovies.model.Movie
import com.jumpywiz.starwarsmovies.model.MovieDB

@Dao
interface Dao {
    @Query("SELECT * FROM moviedb")
    suspend fun getAllMovies(): List<MovieDB>
    @Query("SELECT * FROM moviedb where episode_id = :id")
    suspend fun getMovie(id: Int) : List<MovieDB>
    @Insert
    suspend fun setAllMovies(movies: List<MovieDB>)
}