package com.jumpywiz.starwarsmovies.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.jumpywiz.starwarsmovies.converter.CharacterTypeConverter


@Entity
data class MovieDB(

    val title: String,
    val director: String,
    val producer: String,
    val date: String,
    val charactersURLs: List<String>,
    val episode_id: Int,
    @PrimaryKey(autoGenerate = true) var id: Int = 0
)
