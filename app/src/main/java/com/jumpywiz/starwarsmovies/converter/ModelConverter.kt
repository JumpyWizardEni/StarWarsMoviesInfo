package com.jumpywiz.starwarsmovies.converter

import com.jumpywiz.starwarsmovies.model.CharacterRequest
import com.jumpywiz.starwarsmovies.model.Movie
import com.jumpywiz.starwarsmovies.model.MovieDB
import com.jumpywiz.starwarsmovies.model.MovieData
import com.jumpywiz.starwarsmovies.model.Character

object ModelConverter {
    fun requestToMovie(data: MovieData): Movie {
        return Movie(
            data.title,
            data.director,
            data.producer,
            data.release_date,
            data.episode_id,
            data.characters
        )
    }

    fun requestToMovieDB(data: MovieData): MovieDB {
        return MovieDB(
            data.title,
            data.director,
            data.producer,
            data.release_date,
            data.characters,
            data.episode_id
        )

    }

    fun dbToMovie(data: MovieDB): Movie {
        return Movie(
            data.title,
            data.director,
            data.producer,
            data.date,
            data.episode_id,
            data.charactersURLs
        )
    }

    fun requestToCharacter(data: CharacterRequest): Character {
        return Character(data.name, data.gender, data.birth_year)
    }
}