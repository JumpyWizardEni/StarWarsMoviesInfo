package com.jumpywiz.starwarsmovies.model

import androidx.room.PrimaryKey

data class Movie(
    val title: String?,
    val director: String?,
    val producers: String?,
    val date: String?
)
