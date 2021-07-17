package com.jumpywiz.starwarsmovies.di

import com.jumpywiz.starwarsmovies.db.Dao
import com.jumpywiz.starwarsmovies.net.RemoteServiceImpl
import com.jumpywiz.starwarsmovies.net.RetrofitService
import com.jumpywiz.starwarsmovies.repos.CharacterRepository
import com.jumpywiz.starwarsmovies.repos.MovieInfoRepository
import com.jumpywiz.starwarsmovies.repos.MovieListRepository
import com.jumpywiz.starwarsmovies.repos.Repository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

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