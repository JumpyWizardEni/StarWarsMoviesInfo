package com.jumpywiz.starwarsmovies.db

import com.jumpywiz.starwarsmovies.model.CharacterDB
import com.jumpywiz.starwarsmovies.model.MovieDB
import com.jumpywiz.starwarsmovies.model.PlanetDB
import javax.inject.Inject

class LocalSourceImpl @Inject constructor(private val dao: Dao) : ILocalSource {
    override suspend fun getAllMovies(): List<MovieDB> = dao.getAllMovies()

    override suspend fun getMovie(id: Int): List<MovieDB> = dao.getMovie(id)

    override suspend fun getCharacter(url: String): List<CharacterDB> = dao.getCharacter(url)

    override suspend fun getPlanet(url: String): List<PlanetDB> = dao.getPlanet(url)

    override suspend fun setAllMovies(movies: List<MovieDB>) = dao.setAllMovies(movies)

    override suspend fun setCharacter(character: CharacterDB) = dao.setCharacter(character)

    override suspend fun setPlanet(planet: PlanetDB) = dao.setPlanet(planet)


}