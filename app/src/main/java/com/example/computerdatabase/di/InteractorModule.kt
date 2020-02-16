package com.example.computerdatabase.di

import com.example.computerdatabase.interactor.ComputerDbInteractor
import com.example.computerdatabase.interactor.ComputerDbInteractorInterface
import com.example.computerdatabase.interactor.ComputerDbOfflineDecorator
import com.example.computerdatabase.repository.DatabaseRepositoryInterface
import com.example.computerdatabase.repository.NetworkRepositoryInterface
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
object InteractorModule {

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideMapInteractor(networkRepository: NetworkRepositoryInterface, databaseRepository: DatabaseRepositoryInterface): ComputerDbInteractorInterface {
        return ComputerDbOfflineDecorator(databaseRepository, ComputerDbInteractor(networkRepository))
    }
}