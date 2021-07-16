package com.jumpywiz.starwarsmovies.repos

import com.jumpywiz.starwarsmovies.converter.ModelConverter.dbToCharacter
import com.jumpywiz.starwarsmovies.converter.ModelConverter.dbToPlanet
import com.jumpywiz.starwarsmovies.converter.ModelConverter.requestToCharacterDB
import com.jumpywiz.starwarsmovies.converter.ModelConverter.requestToPlanet
import com.jumpywiz.starwarsmovies.converter.ModelConverter.requestToPlanetDB
import com.jumpywiz.starwarsmovies.db.Dao
import com.jumpywiz.starwarsmovies.model.Character
import com.jumpywiz.starwarsmovies.model.CharacterDB
import com.jumpywiz.starwarsmovies.model.Planet
import com.jumpywiz.starwarsmovies.model.PlanetRequest
import com.jumpywiz.starwarsmovies.net.RetrofitService
import javax.inject.Inject

class CharacterRepository (
    private val dao: Dao,
    private val retrofit: RetrofitService
) :
    Repository {

    suspend fun getCharAndPlanetInfo(charUrl: String): Pair<Character, Planet> {
        /*At this screen character must be in the DB, but in unique cases
        it can be DB miss, so check it*/
        val charList = dao.getCharacter(charUrl)

            val character = charList[0]
            val planetList = dao.getPlanet(character.planet)
            if (planetList.isEmpty()) { //planet is not downloaded yet
                getPlanetInfo(character)
                return Pair(dbToCharacter(character), requestToPlanet(getPlanetInfo(character)))
            } else {
                return Pair(dbToCharacter(character), dbToPlanet(planetList[0]))
            }
    }

    private suspend fun getPlanetInfo(char: CharacterDB): PlanetRequest {
        val splitString = char.planet.split("/")
        val request = retrofit.getPlanetInfo(splitString[splitString.size - 2].toInt())
        dao.setPlanet(requestToPlanetDB(request!!))
        return request
    }
}