package com.example.computerdatabase.di

import com.example.computerdatabase.api.NetworkService
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

//    @Provides
//    @Reusable
//    @JvmStatic
//    internal fun provideDatabaseRepository(
//        database: AppDatabase,
//        partnerDbMapper: PartnerDbMapper,
//        pointDbMapper: PointDbMapper,
//        pointWithPartnerDbMapper: PointWithPartnerDbMapper
//    ): DatabaseRepositoryInterface {
//        return DatabaseRepository(
//            database,
//            partnerDbMapper,
//            pointDbMapper,
//            pointWithPartnerDbMapper
//        )
//    }
}