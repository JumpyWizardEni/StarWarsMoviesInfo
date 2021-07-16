package com.jumpywiz.starwarsmovies.di

import com.jumpywiz.starwarsmovies.db.Dao
import com.jumpywiz.starwarsmovies.net.RemoteServiceImpl
import com.jumpywiz.starwarsmovies.net.RetrofitService
import com.jumpywiz.starwarsmovies.repos.CharacterRepository
import com.jumpywiz.starwarsmovies.repos.MovieInfoRepository
import com.jumpywiz.starwarsmovies.repos.MovieListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideMovieListRepos(dao: Dao, remote: RemoteServiceImpl) =
        MovieListRepository(dao, remote)

    @Provides
    @Singleton
    fun provideMovieInfoRepos(dao: Dao, remote: RemoteServiceImpl) =
        MovieInfoRepository(dao, remote)

    @Provides
    @Singleton
    fun provideCharRepos(dao: Dao, remote: RemoteServiceImpl) =
        CharacterRepository(dao, remote)
}