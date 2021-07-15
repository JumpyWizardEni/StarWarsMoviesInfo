package com.jumpywiz.starwarsmovies.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CharacterDB(
    val name: String,
    val sex: String,
    val birthDate: String,
    val url: String,
    val planet: String,
    @PrimaryKey(autoGenerate = true) var id: Int = 0
)