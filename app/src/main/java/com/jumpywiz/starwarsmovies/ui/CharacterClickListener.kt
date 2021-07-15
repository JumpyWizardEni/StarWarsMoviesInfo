package com.jumpywiz.starwarsmovies.ui

import android.os.Bundle
import android.view.View
import com.jumpywiz.starwarsmovies.R

class CharacterClickListener (private val url: String, private val nav: (Int, Bundle?) -> Unit): View.OnClickListener {
    override fun onClick(p0: View?) {
        val bundle = Bundle().apply {
            putString("CHAR_URL", url)
        }
        nav(R.id.characterInfoFragment, bundle)
    }
}