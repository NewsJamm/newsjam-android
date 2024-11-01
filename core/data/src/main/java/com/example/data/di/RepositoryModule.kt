package com.example.data.di

import com.example.data.repository.account.AccountRepository
import com.example.data.repository.account.AccountRepositoryImpl
import com.example.data.repository.account.remote.AccountDataSource
import com.example.data.repository.account.remote.AccountDataSourceImpl
import com.example.data.repository.home.HomeRepository
import com.example.data.repository.home.HomeRepositoryImpl
import com.example.data.repository.home.remote.HomeDataSource
import com.example.data.repository.home.remote.HomeDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindAccountDataSource(
        accountDataSourceImpl: AccountDataSourceImpl
    ): AccountDataSource

    @Singleton
    @Binds
    abstract fun bindHomeDataSource(
        homeDataSourceImpl: HomeDataSourceImpl
    ): HomeDataSource


    companion object {
        @Singleton
        @Provides
        fun provideAccountRepository(accountDataSource: AccountDataSource): AccountRepository =
            AccountRepositoryImpl(accountDataSource)

        @Singleton
        @Provides
        fun provideHomeRepository(homeDataSource: HomeDataSource): HomeRepository =
            HomeRepositoryImpl(homeDataSource)
    }
}
