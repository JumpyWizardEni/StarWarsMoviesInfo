package com.jumpywiz.starwarsmovies.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jumpywiz.starwarsmovies.viewmodels.MovieInfoViewModel
import com.jumpywiz.starwarsmovies.viewmodels.MovieListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MovieListViewModel::class)
    internal abstract fun postMovieListViewModel(viewModel: MovieListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MovieInfoViewModel::class)
    internal abstract fun postMovieInfoViewModel(viewModel: MovieInfoViewModel): ViewModel
}