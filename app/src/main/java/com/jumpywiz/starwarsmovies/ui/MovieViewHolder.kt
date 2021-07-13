package com.jumpywiz.starwarsmovies.ui

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jumpywiz.starwarsmovies.R
import com.jumpywiz.starwarsmovies.databinding.MovieListViewHolderBinding

class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val title = itemView.findViewById(R.id.title_text) as TextView
    val director = itemView.findViewById(R.id.director_text) as TextView
    val producer = itemView.findViewById(R.id.producer_text) as TextView
    val date = itemView.findViewById(R.id.date_text) as TextView
}