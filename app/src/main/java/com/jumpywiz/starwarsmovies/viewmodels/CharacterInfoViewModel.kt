package com.jumpywiz.starwarsmovies.viewmodels

import androidx.lifecycle.*
import com.jumpywiz.starwarsmovies.model.Character
import com.jumpywiz.starwarsmovies.model.Planet
import com.jumpywiz.starwarsmovies.repos.CharacterRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch

class CharacterInfoViewModel @AssistedInject constructor(
    private val repos: CharacterRepository,
    @Assisted private val charUrl: String
) : ViewModel() {
    private val charData: MutableLiveData<Pair<Character, Planet>> = MutableLiveData()
    val char: LiveData<Pair<Character, Planet>> = charData

    init {
        getCharInfo()
    }

    fun getCharInfo() {
        viewModelScope.launch {
            charData.value = repos.getCharAndPlanetInfo(charUrl)
        }
    }

    @dagger.assisted.AssistedFactory
    interface AssistedFactory {
        fun create(url: String): CharacterInfoViewModel
    }

    companion object {
        fun provideFactory(
            assistedFactory: AssistedFactory,
            url: String
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return assistedFactory.create(url) as T
            }
        }
    }

}