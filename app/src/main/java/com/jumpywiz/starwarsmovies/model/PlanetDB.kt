package com.jumpywiz.starwarsmovies.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PlanetDB(
    val name: String,
    val diameter: Int,
    val climate: String,
    val gravity: String,
    val terrain: String,
    val population: Int,
    val url: String,
    @PrimaryKey(autoGenerate = true) var id: Int = 0
)