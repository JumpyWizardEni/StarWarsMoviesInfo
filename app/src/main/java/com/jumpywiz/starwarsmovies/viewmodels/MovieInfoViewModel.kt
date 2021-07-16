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
import com.jumpywiz.starwarsmovies.net.Result

@HiltViewModel
class MovieInfoViewModel @Inject constructor(
    private val repos: MovieInfoRepository
) : ViewModel() {
    private val charsData: MutableLiveData<List<Character>> = MutableLiveData()
    val chars: LiveData<List<Character>> = charsData

    private val isLoadingData: MutableLiveData<Boolean> = MutableLiveData()
    val isLoading = isLoadingData as LiveData<Boolean>

    var id: Int = 0

    fun getChars() {
        isLoadingData.value = true
        viewModelScope.launch {
            when(val result = repos.getChars(id)) {
                is Result.Success -> {
                    isLoadingData.postValue(false)
                    charsData.value = result.data
                }
                is Result.Loading -> {
                    isLoadingData.postValue(true)
                }
            }
        }
    }

    fun setNewId(newId: Int) {
        id = newId
        getChars()
    }
}