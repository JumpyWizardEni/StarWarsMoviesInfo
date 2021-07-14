package com.jumpywiz.starwarsmovies.converter

import com.jumpywiz.starwarsmovies.model.Movie
import com.jumpywiz.starwarsmovies.model.MovieDB
import com.jumpywiz.starwarsmovies.model.MovieData

object ModelConverter {
    fun requestToMovie(data: MovieData?): Movie? {
        return if (data == null) {
            null
        } else {
            Movie(data.title, data.director, data.producer, data.release_date)
        }
    }

    fun requestToMovieDB(data: MovieData?): MovieDB? {
        return if (data == null) {
            null
        } else {
            MovieDB(data.title, data.director, data.producer, data.release_date, data.characters)
        }
    }

    fun dbToMovie(data: MovieDB?): Movie? {
        return if (data == null) {
            null
        } else {
            Movie(data.title, data.director, data.producer, data.date)
        }
    }
}