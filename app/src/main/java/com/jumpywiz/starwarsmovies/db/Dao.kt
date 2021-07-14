package com.jumpywiz.starwarsmovies.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.jumpywiz.starwarsmovies.model.MovieDB

@Dao
interface Dao {
    @Query("SELECT * FROM moviedb")
    suspend fun getAllMovies(): List<MovieDB>
    @Insert
    suspend fun setAllMovies(movies: List<MovieDB?>)
}