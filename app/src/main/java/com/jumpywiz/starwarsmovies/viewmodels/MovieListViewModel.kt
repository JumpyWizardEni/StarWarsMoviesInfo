package com.jumpywiz.starwarsmovies.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jumpywiz.starwarsmovies.model.Movie
import com.jumpywiz.starwarsmovies.repos.MovieListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.jumpywiz.starwarsmovies.net.Result


@HiltViewModel
class MovieListViewModel @Inject constructor(private val repos: MovieListRepository) : ViewModel() {

    private val moviesData: MutableLiveData<List<Movie>> = MutableLiveData()
    val movies: LiveData<List<Movie>> = moviesData

    private val isLoadingData: MutableLiveData<Boolean> = MutableLiveData()
    val isLoading = isLoadingData as LiveData<Boolean>

    init {
        getMoviesList()
        Log.d("Init object", "[MovieListViewModel::]Initing viewModel")
    }

    fun getMoviesList() {
        isLoadingData.value = true
        viewModelScope.launch {
            when (val result = repos.getMoviesList()) {
                is Result.Success -> {
                    isLoadingData.postValue(false)
                    moviesData.value = result.data
                }
                is Result.Loading -> isLoadingData.postValue(true)
            }
        }
    }

}