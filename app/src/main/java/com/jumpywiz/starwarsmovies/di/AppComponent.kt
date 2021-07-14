package com.jumpywiz.starwarsmovies.di

import com.jumpywiz.starwarsmovies.ui.fragments.CharacterInfoFragment
import com.jumpywiz.starwarsmovies.ui.fragments.MovieInfoFragment
import com.jumpywiz.starwarsmovies.ui.fragments.MovieListFragment
import com.jumpywiz.starwarsmovies.viewmodels.MovieListViewModel
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = arrayOf(
        AppModule::class,
        DatabaseModule::class,
        NetModule::class,
        RepositoryModule::class,
        ViewModelModule::class
    )
)
@Singleton
interface AppComponent {
    fun inject(movieListFragment: MovieListFragment)
    fun inject(movieInfoFragment: MovieInfoFragment)
    fun inject(characterInfoFragment: CharacterInfoFragment)
    fun inject(viewModel: MovieListViewModel)
}