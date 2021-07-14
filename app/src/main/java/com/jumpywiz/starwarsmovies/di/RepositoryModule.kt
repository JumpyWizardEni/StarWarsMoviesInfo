package com.jumpywiz.starwarsmovies.di

import com.jumpywiz.starwarsmovies.db.Dao
import com.jumpywiz.starwarsmovies.net.RetrofitService
import com.jumpywiz.starwarsmovies.repos.MovieListRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun provideMovieRepos(dao: Dao, retrofitService: RetrofitService) =
        MovieListRepository(dao, retrofitService)
}