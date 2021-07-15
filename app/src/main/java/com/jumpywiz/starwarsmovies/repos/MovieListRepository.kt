package com.jumpywiz.starwarsmovies.repos

import android.util.Log
import com.jumpywiz.starwarsmovies.converter.ModelConverter.dbToMovie
import com.jumpywiz.starwarsmovies.converter.ModelConverter.requestToMovie
import com.jumpywiz.starwarsmovies.converter.ModelConverter.requestToMovieDB
import com.jumpywiz.starwarsmovies.db.Dao
import com.jumpywiz.starwarsmovies.model.Movie
import com.jumpywiz.starwarsmovies.model.MovieDB
import com.jumpywiz.starwarsmovies.net.RetrofitService
import javax.inject.Inject

class MovieListRepository @Inject constructor(
    private val dao: Dao,
    private val retrofit: RetrofitService
) :
    Repository {

    suspend fun getMoviesList(): List<Movie> {
        val movies = dao.getAllMovies()
        val data: MutableList<Movie> = mutableListOf()
        if (movies.isEmpty()) {
            Log.d("[MovieListRepository]", "Movies list from From Net")
            val request = retrofit.getMoviesList()
            return if (request != null) {

                val dbData: MutableList<MovieDB> = mutableListOf()
                request.results.forEach { rawData ->
                    data.add(requestToMovie(rawData))
                    dbData.add(requestToMovieDB(rawData))
                }
                dao.setAllMovies(dbData)
                data
            } else listOf()
        } else {
            Log.d("[MovieListRepository]", "Movies list from From Database")
            movies.forEach { movie ->
                data.add(dbToMovie(movie))
            }
            return data
        }

    }

}