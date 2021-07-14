package com.jumpywiz.starwarsmovies.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jumpywiz.starwarsmovies.model.Movie
import com.jumpywiz.starwarsmovies.repos.MovieListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(private val repos: MovieListRepository): ViewModel(){

    private val moviesData: MutableLiveData<List<Movie>> = MutableLiveData()
    val movies: LiveData<List<Movie>> = moviesData

    private val movieData: MutableLiveData<Movie> = MutableLiveData()
    val movie: LiveData<Movie> = MutableLiveData()
    init {
        getMoviesList()
    }

    fun getMoviesList() {
        viewModelScope.launch {
            moviesData.value = repos.getMoviesList()
        }
    }

}