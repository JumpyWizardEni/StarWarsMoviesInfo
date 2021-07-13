package com.jumpywiz.starwarsmovies

import android.app.Application
import com.jumpywiz.starwarsmovies.di.AppComponent
import com.jumpywiz.starwarsmovies.di.AppModule
import com.jumpywiz.starwarsmovies.di.DaggerAppComponent
import com.jumpywiz.starwarsmovies.di.DatabaseModule

class App: Application() {
    companion object {
        lateinit var appComponent: AppComponent
    }
    override fun onCreate() {
        super.onCreate()
        appComponent =
            DaggerAppComponent.create()
    }
}