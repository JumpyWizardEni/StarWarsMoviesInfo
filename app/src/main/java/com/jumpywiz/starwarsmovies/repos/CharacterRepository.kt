package com.jumpywiz.starwarsmovies.repos

import com.jumpywiz.starwarsmovies.db.Dao
import com.jumpywiz.starwarsmovies.net.RetrofitService

class CharacterRepository(private val dao: Dao, private val retrofit: RetrofitService) :
    Repository {

}