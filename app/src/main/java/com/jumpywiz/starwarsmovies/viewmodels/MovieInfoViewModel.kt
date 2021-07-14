package com.jumpywiz.starwarsmovies.viewmodels

import androidx.lifecycle.*
import com.jumpywiz.starwarsmovies.model.Character
import com.jumpywiz.starwarsmovies.repos.MovieInfoRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieInfoViewModel @AssistedInject constructor(
    private val repos: MovieInfoRepository,
    @Assisted private val id: Int
) : ViewModel() {
    private val charsData: MutableLiveData<List<Character>> = MutableLiveData()
    val chars: LiveData<List<Character>> = charsData




    init {
        getChars()
    }

    fun getChars() {
        viewModelScope.launch {
            charsData.value = repos.getChars(id)
        }
    }

    @dagger.assisted.AssistedFactory
    interface AssistedFactory {
        fun create(id: Int): MovieInfoViewModel
    }

    companion object {
        fun provideFactory(
            assistedFactory: AssistedFactory,
            id: Int
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return assistedFactory.create(id) as T
            }
        }
    }

}