package com.jumpywiz.starwarsmovies.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class MovieDB(

    val title: String,
    val director: String,
    val producer: String,
    val date: String,
    val charactersURLs: List<String>,
    val episodeId: Int,
    @PrimaryKey(autoGenerate = true) var id: Int = 0
)
