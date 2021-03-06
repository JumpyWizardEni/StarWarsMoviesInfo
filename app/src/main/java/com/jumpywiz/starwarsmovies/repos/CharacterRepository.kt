package com.jumpywiz.starwarsmovies.repos

import com.jumpywiz.starwarsmovies.converter.ModelConverter.dbToCharacter
import com.jumpywiz.starwarsmovies.converter.ModelConverter.dbToPlanet
import com.jumpywiz.starwarsmovies.converter.ModelConverter.requestToPlanet
import com.jumpywiz.starwarsmovies.converter.ModelConverter.requestToPlanetDB
import com.jumpywiz.starwarsmovies.db.LocalSourceImpl
import com.jumpywiz.starwarsmovies.model.Character
import com.jumpywiz.starwarsmovies.model.Planet
import com.jumpywiz.starwarsmovies.net.IRemoteService
import com.jumpywiz.starwarsmovies.net.Result
import javax.inject.Inject

class CharacterRepository @Inject constructor(
    private val local: LocalSourceImpl,
    private val remote: IRemoteService
) :
    Repository {
    suspend fun getCharAndPlanetInfo(charUrl: String): Result<Pair<Character, Planet>> {
        val charList = local.getCharacter(charUrl)
        val character = charList[0]
        val planetList = local.getPlanet(character.planet)
        if (planetList.isEmpty()) { //planet is not downloaded yet
            val splitString = character.planet.split("/")
            return when (val request =
                remote.getPlanetInfo(splitString[splitString.size - 2].toInt())) {

                is Result.Success -> {
                    local.setPlanet(requestToPlanetDB(request.data!!))
                    Result.Success(
                        Pair(
                            dbToCharacter(character),
                            requestToPlanet(request.data)
                        )
                    )
                }
                is Result.Error -> {
                    Result.Error(request.exception)
                }
                is Result.Loading -> {
                    Result.Loading
                }
            }
        } else {
            return Result.Success(Pair(dbToCharacter(character), dbToPlanet(planetList[0])))
        }
    }

}