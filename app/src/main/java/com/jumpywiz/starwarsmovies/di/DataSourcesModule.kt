package com.jumpywiz.starwarsmovies.di

import com.jumpywiz.starwarsmovies.db.ILocalSource
import com.jumpywiz.starwarsmovies.db.LocalSourceImpl
import com.jumpywiz.starwarsmovies.net.IRemoteService
import com.jumpywiz.starwarsmovies.net.RemoteServiceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourcesModule {

    @Binds
    abstract fun bindLocalDataSource(localData: LocalSourceImpl): ILocalSource

    @Binds
    abstract fun bindRemoteDataSource(localRemote: RemoteServiceImpl): IRemoteService
}
