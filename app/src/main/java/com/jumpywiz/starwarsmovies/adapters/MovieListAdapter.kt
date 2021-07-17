package com.jumpywiz.starwarsmovies.adapters

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.jumpywiz.starwarsmovies.R
import com.jumpywiz.starwarsmovies.model.Movie
import com.jumpywiz.starwarsmovies.ui.MovieClickListener
import com.jumpywiz.starwarsmovies.ui.MovieViewHolder

class MovieListAdapter(private val nav: (Int, Bundle?) -> Unit, private val context: Context) :
    RecyclerView.Adapter<MovieViewHolder>(), Filterable {
    private var movies: MutableList<Movie> = mutableListOf()
    private var moviesFiltered: MutableList<Movie> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_list_view_holder, parent, false)
        return MovieViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = moviesFiltered[position]
        holder.title.text = movie.title
        holder.director.text =
            String.format(context.resources.getString(R.string.director), movie.director)
        holder.producer.text =
            String.format(context.resources.getString(R.string.producer), movie.producer)
        holder.date.text = movie.date
        val listener = MovieClickListener(movie.title, movie.episode_id, nav)
        holder.itemView.setOnClickListener(listener)
    }

    override fun getItemCount(): Int {
        return moviesFiltered.size
    }

    fun setData(moviesData: List<Movie>) {
        movies = mutableListOf()
        movies.addAll(moviesData)
        movies.sortWith(object : Comparator<Movie> {
            override fun compare(m0: Movie, m1: Movie): Int = when {
                m0.episode_id > m1.episode_id -> 1
                m0.episode_id == m1.episode_id -> 0
                else -> -1
            }
        })
        moviesFiltered.addAll(movies)

        notifyDataSetChanged()
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(p0: CharSequence?): FilterResults {
                val filter = p0.toString().lowercase()
                if (filter.isEmpty()) {
                    moviesFiltered = movies
                } else {
                    val filteredList = mutableListOf<Movie>()
                    movies.forEach {
                        if (it.title.lowercase().contains(filter)) {
                            filteredList.add(it)
                        }
                    }
                    moviesFiltered = filteredList
                }
                val result = FilterResults()
                result.values = moviesFiltered
                return result
            }

            override fun publishResults(p0: CharSequence?, filtered: FilterResults?) {
                moviesFiltered = filtered!!.values as MutableList<Movie>
                notifyDataSetChanged()
            }

        }
    }


}