package com.jumpywiz.starwarsmovies.di

import com.jumpywiz.starwarsmovies.net.RetrofitService
import com.jumpywiz.starwarsmovies.repos.MovieRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun provideRepos(retrofitService: RetrofitService) = MovieRepository(retrofitService)
}