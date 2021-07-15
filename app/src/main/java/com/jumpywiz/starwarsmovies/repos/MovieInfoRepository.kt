package com.jumpywiz.starwarsmovies.repos

import android.util.Log
import com.jumpywiz.starwarsmovies.converter.ModelConverter
import com.jumpywiz.starwarsmovies.converter.ModelConverter.dbToCharacter
import com.jumpywiz.starwarsmovies.converter.ModelConverter.requestToCharacter
import com.jumpywiz.starwarsmovies.converter.ModelConverter.requestToCharacterDB
import com.jumpywiz.starwarsmovies.db.Dao
import com.jumpywiz.starwarsmovies.net.RetrofitService
import com.jumpywiz.starwarsmovies.model.Character
import javax.inject.Inject

class MovieInfoRepository @Inject constructor(
    private val dao: Dao,
    private val retrofit: RetrofitService
) :
    Repository {
    suspend fun getChars(id: Int): List<Character> {
        val movie = dao.getMovie(id)
        val chars: MutableList<Character> = mutableListOf()
        movie[0].charactersURLs.forEach {
            val strSplit = it.split("/")
            val charList = dao.getCharacter(it)
            if (charList.isEmpty()) {
                val request = retrofit.getCharacterInfo(strSplit[strSplit.size - 2].toInt())
                chars.add(requestToCharacter(request!!)) //TODO(NULL EXCEPTION)
                dao.setCharacter(requestToCharacterDB(request))
            } else {
                chars.add(dbToCharacter(charList[0]))
            }

        }
        return chars
    }

}