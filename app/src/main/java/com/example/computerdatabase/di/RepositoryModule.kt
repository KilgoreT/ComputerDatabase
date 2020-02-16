package com.example.computerdatabase.di

import com.example.computerdatabase.datasource.api.NetworkService
import com.example.computerdatabase.datasource.room.dao.ComputerDetailDao
import com.example.computerdatabase.repository.DatabaseRepository
import com.example.computerdatabase.repository.DatabaseRepositoryInterface
import com.example.computerdatabase.repository.NetworkRepository
import com.example.computerdatabase.repository.NetworkRepositoryInterface
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
object RepositoryModule {
    @Provides
    @Reusable
    @JvmStatic
    internal fun provideNetworkRepository(api: NetworkService): NetworkRepositoryInterface {
        return NetworkRepository(api)
    }

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideDatabaseRepository(computerDetailDao: ComputerDetailDao): DatabaseRepositoryInterface {
        return DatabaseRepository(computerDetailDao)
    }
}