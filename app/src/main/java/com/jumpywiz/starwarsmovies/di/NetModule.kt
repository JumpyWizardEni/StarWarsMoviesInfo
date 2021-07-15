package com.jumpywiz.starwarsmovies.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jumpywiz.starwarsmovies.net.RetrofitService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetModule {
    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson) = Retrofit.Builder()
        .baseUrl(RetrofitService.URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
    @Provides
    fun provideRetrofitService(retrofit: Retrofit) = retrofit.create(RetrofitService::class.java)
    @Provides
    fun provideGsonFactory() = GsonBuilder().setLenient().create()
}