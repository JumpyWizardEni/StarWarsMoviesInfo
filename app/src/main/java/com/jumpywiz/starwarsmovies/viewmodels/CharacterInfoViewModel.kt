package com.jumpywiz.starwarsmovies.viewmodels

import androidx.lifecycle.*
import com.jumpywiz.starwarsmovies.model.Character
import com.jumpywiz.starwarsmovies.model.Planet
import com.jumpywiz.starwarsmovies.net.Result
import com.jumpywiz.starwarsmovies.repos.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterInfoViewModel @Inject constructor(
    private val repos: CharacterRepository
) : ViewModel() {
    private val charData: MutableLiveData<Pair<Character, Planet>> = MutableLiveData()
    val char: LiveData<Pair<Character, Planet>> = charData

    private val isLoadingData: MutableLiveData<Boolean> = MutableLiveData()
    val isLoading = isLoadingData as LiveData<Boolean>

    private val onErrorData: MutableLiveData<Boolean> = MutableLiveData()
    val onError = onErrorData as LiveData<Boolean>

    private var charUrl: String? = null
    fun getCharInfo() {
        isLoadingData.value = true
        onErrorData.value = false
        viewModelScope.launch {
            when(val result = repos.getCharAndPlanetInfo(charUrl!!)) {
                is Result.Success -> {
                    isLoadingData.postValue(false)
                    onErrorData.postValue(false)
                    charData.value = result.data

                }
                is Result.Loading -> {
                    isLoadingData.postValue(true)
                }
                is Result.Error -> {
                    onErrorData.postValue(true)
                    isLoadingData.postValue(false)
                }
            }
        }
    }

    fun setNewURL(url: String) {
        charUrl = url
        getCharInfo()
    }

}