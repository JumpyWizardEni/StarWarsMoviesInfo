package com.jumpywiz.starwarsmovies.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class MovieDB(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String?,
    val director: String?,
    val producers: String?,
    val date: String?,
    val charactersURLs: List<String?>
)
