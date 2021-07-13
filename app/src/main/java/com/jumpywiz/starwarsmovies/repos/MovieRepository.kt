package com.jumpywiz.starwarsmovies.repos

import com.jumpywiz.starwarsmovies.converter.ModelConverter.requestToMovie
import com.jumpywiz.starwarsmovies.model.Movie
import com.jumpywiz.starwarsmovies.net.RetrofitService

class MovieRepository(val retrofit: RetrofitService): Repository {

    suspend fun getMoviesList(): List<Movie?> {
        val request = retrofit.getMoviesList()
        return if (request != null) {
            val data: MutableList<Movie?> = mutableListOf()
            request.results.forEach {
                rawData ->
                data.add(requestToMovie(rawData))
            }
            data
        } else listOf()
    }
}