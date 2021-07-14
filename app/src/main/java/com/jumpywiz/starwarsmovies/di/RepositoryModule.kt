package com.jumpywiz.starwarsmovies.di

import com.jumpywiz.starwarsmovies.db.Dao
import com.jumpywiz.starwarsmovies.net.RetrofitService
import com.jumpywiz.starwarsmovies.repos.CharacterRepository
import com.jumpywiz.starwarsmovies.repos.MovieInfoRepository
import com.jumpywiz.starwarsmovies.repos.MovieListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideMovieListRepos(dao: Dao, retrofitService: RetrofitService) =
        MovieListRepository(dao, retrofitService)

    @Provides
    @Singleton
    fun provideMovieInfoRepos(dao: Dao, retrofitService: RetrofitService) =
        MovieInfoRepository(dao, retrofitService)

    @Provides
    @Singleton
    fun provideCharRepos(dao: Dao, retrofitService: RetrofitService) =
        CharacterRepository(dao, retrofitService)
}