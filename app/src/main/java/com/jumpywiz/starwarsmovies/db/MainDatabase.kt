package com.jumpywiz.starwarsmovies.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jumpywiz.starwarsmovies.converter.CharacterTypeConverter
import com.jumpywiz.starwarsmovies.model.MovieDB

@Database(entities = [MovieDB::class], version = 1, exportSchema = false)
@TypeConverters(CharacterTypeConverter::class)

abstract class MainDatabase: RoomDatabase() {
    abstract fun Dao(): Dao
    companion object{
        private var instance: MainDatabase? = null
        fun getInstance(context: Context): MainDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(context.applicationContext,
                    MainDatabase::class.java, "MainDB").fallbackToDestructiveMigration().build() //just for debug
            }
            return instance as MainDatabase
        }
    }
}