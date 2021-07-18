package com.jumpywiz.starwarsmovies.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.jumpywiz.starwarsmovies.R
import com.jumpywiz.starwarsmovies.databinding.ActivityMainBinding
import com.jumpywiz.starwarsmovies.viewmodels.CharacterInfoViewModel
import com.jumpywiz.starwarsmovies.viewmodels.MovieInfoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    //ViewModels not outlive fragment destruction,
    //but outlive activity destruction after rotation
    private val movieListViewModel: ViewModel by viewModels()

    private val movieInfoViewModel: MovieInfoViewModel by viewModels()

    private val characterViewModel: CharacterInfoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.movieInfoToolbar)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        if (savedInstanceState == null) {
            navController.navigate(R.id.movieListFragment)
        }
    }


}