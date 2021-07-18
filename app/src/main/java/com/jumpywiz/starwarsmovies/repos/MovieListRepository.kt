package com.jumpywiz.starwarsmovies.repos

import android.util.Log
import com.jumpywiz.starwarsmovies.converter.ModelConverter.dbToMovie
import com.jumpywiz.starwarsmovies.converter.ModelConverter.requestToMovie
import com.jumpywiz.starwarsmovies.converter.ModelConverter.requestToMovieDB
import com.jumpywiz.starwarsmovies.db.LocalSourceImpl
import com.jumpywiz.starwarsmovies.model.Movie
import com.jumpywiz.starwarsmovies.model.MovieDB
import com.jumpywiz.starwarsmovies.net.IRemoteService
import com.jumpywiz.starwarsmovies.net.Result
import javax.inject.Inject

class MovieListRepository @Inject constructor(
    private val local: LocalSourceImpl,
    private val remote: IRemoteService
) :
    Repository {

    suspend fun getMoviesList(): Result<List<Movie>> {
        val movies = local.getAllMovies()
        val data: MutableList<Movie> = mutableListOf()
        if (movies.isEmpty()) {
            Log.d("[MovieListRepository]", "Movies list from From Net")
            when (val request = remote.getMoviesList()) {
                is Result.Success -> {
                    return if (request.data != null) {
                        val dbData: MutableList<MovieDB> = mutableListOf()
                        request.data.results.forEach { rawData ->
                            data.add(requestToMovie(rawData))
                            dbData.add(requestToMovieDB(rawData))
                        }
                        local.setAllMovies(dbData)
                        Result.Success(data)
                    } else {
                        Result.Success(listOf())
                    }
                }
                is Result.Error -> {
                    return Result.Error(request.exception)
                }
                else -> {
                    return Result.Loading
                }

            }
        } else {
            Log.d("[MovieListRepository]", "Movies list from From Database")
            movies.forEach { movie ->
                data.add(dbToMovie(movie))
            }
            return Result.Success<List<Movie>>(data)
        }

    }

}