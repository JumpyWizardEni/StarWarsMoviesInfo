package com.jumpywiz.starwarsmovies.di

import com.jumpywiz.starwarsmovies.repos.CharacterRepository
import com.jumpywiz.starwarsmovies.repos.MovieInfoRepository
import com.jumpywiz.starwarsmovies.repos.MovieListRepository
import com.jumpywiz.starwarsmovies.repos.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideMovieListRepos(movieListRepo: MovieListRepository): Repository

    @Binds
    abstract fun provideMovieInfoRepos(movieInfoRepo: MovieInfoRepository): Repository

    @Binds
    abstract fun provideCharRepos(charRepo: CharacterRepository): Repository
}