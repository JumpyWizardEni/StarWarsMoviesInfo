package com.jumpywiz.starwarsmovies.ui

import android.os.Bundle
import android.view.View
import com.jumpywiz.starwarsmovies.R

class MovieClickListener(private val nav: (Int, Bundle?) -> Unit): View.OnClickListener {
    var characterList: List<String> = listOf()
    override fun onClick(p0: View?) {
        val bundle = Bundle().apply {
            putStringArrayList("CHARS", ArrayList<String>(characterList))
        }
        nav(R.id.movieInfoFragment, bundle)
    }
}