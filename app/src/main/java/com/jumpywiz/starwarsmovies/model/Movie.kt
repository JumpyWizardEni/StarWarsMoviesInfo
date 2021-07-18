package com.jumpywiz.starwarsmovies.model

data class Movie(
    val title: String,
    val director: String,
    val producer: String,
    val date: String,
    val episode_id: Int,
    val characterURLs: List<String>
)
