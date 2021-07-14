package com.jumpywiz.starwarsmovies.ui

import android.os.Bundle
import android.view.View
import com.jumpywiz.starwarsmovies.R

class MovieClickListener(private val nav: (Int, Bundle?) -> Unit): View.OnClickListener {
    var id = 0
    override fun onClick(p0: View?) {
        val bundle = Bundle().apply {
            putInt("MOVIE_ID", id)
        }
        nav(R.id.movieInfoFragment, bundle)
    }
}