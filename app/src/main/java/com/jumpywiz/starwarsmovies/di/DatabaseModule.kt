package com.jumpywiz.starwarsmovies.di

import android.content.Context
import com.jumpywiz.starwarsmovies.db.MainDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDB(context: Context) = MainDatabase.getInstance(context)

    @Provides
    fun provideDAO(db: MainDatabase) = db.Dao()
}