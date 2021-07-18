package com.jumpywiz.starwarsmovies.di

import android.content.Context
import com.jumpywiz.starwarsmovies.db.MainDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDB(@ApplicationContext context: Context) = MainDatabase.getInstance(context)

    @Provides
    @Singleton
    fun provideDAO(db: MainDatabase) = db.Dao()

}