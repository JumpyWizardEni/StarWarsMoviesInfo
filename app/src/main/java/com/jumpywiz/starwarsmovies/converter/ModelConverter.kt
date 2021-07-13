package com.jumpywiz.starwarsmovies.converter

import com.jumpywiz.starwarsmovies.model.Movie
import com.jumpywiz.starwarsmovies.model.MovieData

object ModelConverter {
    fun requestToMovie(data: MovieData?): Movie? {
        return if (data == null) {
            null
        } else {
            Movie(data.title, data.director, data.producer, data.release_date)
        }
    }
}