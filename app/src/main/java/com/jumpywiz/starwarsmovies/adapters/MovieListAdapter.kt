package com.jumpywiz.starwarsmovies.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jumpywiz.starwarsmovies.R
import com.jumpywiz.starwarsmovies.model.Movie
import com.jumpywiz.starwarsmovies.ui.MovieClickListener
import com.jumpywiz.starwarsmovies.ui.MovieViewHolder

class MovieListAdapter(private val listener: MovieClickListener) : RecyclerView.Adapter<MovieViewHolder>() {
    private var movies: MutableList<Movie?> = mutableListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_list_view_holder, parent, false)
        return MovieViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]!!
        holder.title.text = movie.title
        holder.director.text = movie.director
        holder.producer.text = movie.producer
        holder.date.text = movie.date

        listener.characterList = movie.characterURLs
        holder.itemView.setOnClickListener(listener)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    fun setData(moviesData: List<Movie?>) {
        movies = mutableListOf()
        movies.addAll(moviesData)
        notifyDataSetChanged()
    }

}