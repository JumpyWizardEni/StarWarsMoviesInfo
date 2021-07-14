package com.jumpywiz.starwarsmovies.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jumpywiz.starwarsmovies.model.Character
import com.jumpywiz.starwarsmovies.repos.MovieInfoRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieInfoViewModel @Inject constructor(private val repos: MovieInfoRepository): ViewModel() {
    private val charsData: MutableLiveData<List<Character>> = MutableLiveData()
    val chars: LiveData<List<Character>> = charsData
    var id: Int = 1
    init {
        getChars()
    }

    fun getChars() {
        viewModelScope.launch {
            charsData.value = repos.getChars(id)
        }
    }

}