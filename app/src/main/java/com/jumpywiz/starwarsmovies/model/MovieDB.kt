package com.jumpywiz.starwarsmovies.models

data class MovieDB(
    val title: String?,
    val director: String?,
    val producers: String?,
    val date: String?,
    val charactersURLs: List<String?>
)
