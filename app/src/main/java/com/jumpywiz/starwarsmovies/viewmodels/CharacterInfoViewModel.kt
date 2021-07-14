package com.jumpywiz.starwarsmovies.viewmodels

import androidx.lifecycle.ViewModel
import com.jumpywiz.starwarsmovies.repos.CharacterRepository
import javax.inject.Inject

class CharacterInfoViewModel @Inject constructor(repos: CharacterRepository): ViewModel() {
}