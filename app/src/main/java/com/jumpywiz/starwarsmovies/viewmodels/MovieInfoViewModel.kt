package com.jumpywiz.starwarsmovies.viewmodels

import android.util.Log
import androidx.lifecycle.*
import com.jumpywiz.starwarsmovies.model.Character
import com.jumpywiz.starwarsmovies.repos.MovieInfoRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieInfoViewModel @Inject constructor(
    private val repos: MovieInfoRepository
) : ViewModel() {
    private val charsData: MutableLiveData<List<Character>> = MutableLiveData()
    val chars: LiveData<List<Character>> = charsData
    var id: Int = 0

    fun getChars() {
        viewModelScope.launch {
            charsData.value = repos.getChars(id)
        }
    }

    fun setNewId(newId: Int) {
        id = newId
        getChars()
    }
}