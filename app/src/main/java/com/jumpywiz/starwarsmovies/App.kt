package com.jumpywiz.starwarsmovies

import android.app.Application
import com.jumpywiz.starwarsmovies.di.*

class App : Application() {
    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent =
            DaggerAppComponent.builder().appModule(AppModule(context = this.applicationContext))
                .databaseModule(
                    DatabaseModule()
                ).netModule(NetModule()).repositoryModule(RepositoryModule())
                .build()
    }
}