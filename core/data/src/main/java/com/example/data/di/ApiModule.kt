package com.example.data.di

import com.example.data.remote.HomeApi
import com.example.data.remote.MainApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Provides
    @Singleton
    fun provideMainServer(
        @MainRetrofit retrofit: Retrofit
    ): MainApi = retrofit.create(MainApi::class.java)


    @Provides
    @Singleton
    fun provideHome(
        @MainRetrofit retrofit: Retrofit
    ): HomeApi = retrofit.create(HomeApi::class.java)
}