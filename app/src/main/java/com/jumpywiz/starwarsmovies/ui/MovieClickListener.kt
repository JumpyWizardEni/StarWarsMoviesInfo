package com.jumpywiz.starwarsmovies.ui

import android.os.Bundle
import android.view.View
import com.jumpywiz.starwarsmovies.R

class MovieClickListener(
    private val movie: String,
    private val id: Int,
    private val nav: (Int, Bundle?) -> Unit
) : View.OnClickListener {
    override fun onClick(p0: View?) {
        val bundle = Bundle().apply {
            putInt("MOVIE_ID", id)
            putString("MOVIE_TITLE", movie)
        }
        nav(R.id.movieInfoFragment, bundle)
    }
}