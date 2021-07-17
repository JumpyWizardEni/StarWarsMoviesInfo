package com.jumpywiz.starwarsmovies.repos

import com.jumpywiz.starwarsmovies.converter.ModelConverter.dbToCharacter
import com.jumpywiz.starwarsmovies.converter.ModelConverter.requestToCharacter
import com.jumpywiz.starwarsmovies.converter.ModelConverter.requestToCharacterDB
import com.jumpywiz.starwarsmovies.db.Dao
import com.jumpywiz.starwarsmovies.db.LocalSourceImpl
import com.jumpywiz.starwarsmovies.model.Character
import com.jumpywiz.starwarsmovies.net.IRemoteService
import com.jumpywiz.starwarsmovies.net.Result

import javax.inject.Inject

class MovieInfoRepository @Inject constructor(
    private val local: LocalSourceImpl,
    private val remote: IRemoteService
) :
    Repository {
    suspend fun getChars(id: Int): Result<List<Character>> {
        val movie = local.getMovie(id)
        val chars: MutableList<Character> = mutableListOf()
        movie[0].charactersURLs.forEach {
            val strSplit = it.split("/")
            val charList = local.getCharacter(it)
            if (charList.isEmpty()) {
                when(val request = remote.getCharacterInfo(strSplit[strSplit.size - 2].toInt())){
                    is Result.Success -> {
                        chars.add(requestToCharacter(request.data!!))
                        local.setCharacter(requestToCharacterDB(request.data))
                    }
                    is Result.Error -> {
                        return Result.Error(request.exception)
                    }
                    is Result.Loading -> {
                        return Result.Loading
                    }
                }
            } else {
                chars.add(dbToCharacter(charList[0]))
            }

        }
        return Result.Success(chars)
    }

}