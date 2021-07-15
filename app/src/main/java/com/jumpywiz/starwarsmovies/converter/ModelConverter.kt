package com.jumpywiz.starwarsmovies.converter

import com.jumpywiz.starwarsmovies.model.*

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
            data.episodeId,
            data.charactersURLs
        )
    }

    fun requestToCharacter(data: CharacterRequest): Character {
        return Character(data.name, data.gender, data.birth_year, data.url, data.homeworld)
    }

    fun requestToCharacterDB(data: CharacterRequest): CharacterDB {
        return CharacterDB(data.name, data.gender, data.birth_year, data.url, data.homeworld)
    }

    fun dbToCharacter(data: CharacterDB): Character {
        return Character(data.name, data.sex, data.birthDate, data.url, data.planet)
    }

    fun requestToPlanet(data: PlanetRequest): Planet {
        return Planet(
            data.name,
            data.diameter,
            data.climate,
            data.gravity,
            data.terrain,
            data.population
        )
    }

    fun requestToPlanetDB(data: PlanetRequest): PlanetDB {
        return PlanetDB(
            data.name,
            data.diameter,
            data.climate,
            data.gravity,
            data.terrain,
            data.population,
            data.url
        )
    }

    fun dbToPlanet(data: PlanetDB): Planet {
        return Planet(
            data.name,
            data.diameter,
            data.climate,
            data.gravity,
            data.terrain,
            data.population
        )
    }

}