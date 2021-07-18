package com.jumpywiz.starwarsmovies.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jumpywiz.starwarsmovies.model.Character
import com.jumpywiz.starwarsmovies.net.Result
import com.jumpywiz.starwarsmovies.repos.MovieInfoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieInfoViewModel @Inject constructor(
    private val repos: MovieInfoRepository
) : ViewModel() {
    private val charsData: MutableLiveData<List<Character>> = MutableLiveData()
    val chars: LiveData<List<Character>> = charsData

    private val isLoadingData: MutableLiveData<Boolean> = MutableLiveData()
    val isLoading = isLoadingData as LiveData<Boolean>

    private val onErrorData: MutableLiveData<Boolean> = MutableLiveData()
    val onError = onErrorData as LiveData<Boolean>

    var id: Int = 0

    fun getChars() {
        isLoadingData.value = true
        onErrorData.value = false
        viewModelScope.launch {
            when (val result = repos.getChars(id)) {
                is Result.Success -> {
                    isLoadingData.postValue(false)
                    charsData.value = result.data
                    onErrorData.postValue(false)
                }
                is Result.Loading -> {
                    isLoadingData.postValue(true)
                }
                is Result.Error -> {
                    isLoadingData.postValue(false)
                    onErrorData.postValue(true)

                }
            }
        }
    }

    fun setNewId(newId: Int) {
        id = newId
        getChars()
    }
}