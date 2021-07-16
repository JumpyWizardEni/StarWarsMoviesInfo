package com.jumpywiz.starwarsmovies.viewmodels

import android.util.Log
import androidx.lifecycle.*
import com.jumpywiz.starwarsmovies.model.Character
import com.jumpywiz.starwarsmovies.model.Planet
import com.jumpywiz.starwarsmovies.repos.CharacterRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterInfoViewModel @Inject constructor(
    private val repos: CharacterRepository
) : ViewModel() {
    private val charData: MutableLiveData<Pair<Character, Planet>> = MutableLiveData()
    val char: LiveData<Pair<Character, Planet>> = charData

    private var charUrl: String? = null
    fun getCharInfo() {
        viewModelScope.launch {
            charData.value = repos.getCharAndPlanetInfo(charUrl!!)
        }
    }

    fun setNewURL(url: String) {
        charUrl = url
        getCharInfo()
    }

}