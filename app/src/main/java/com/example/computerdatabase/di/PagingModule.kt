package com.example.computerdatabase.di

import androidx.paging.PagedList
import com.example.computerdatabase.BuildConfig
import com.example.computerdatabase.interactor.ComputerDbInteractorInterface
import com.example.computerdatabase.ui.computerList.ComputerListDataSourceFactory
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
object PagingModule {

    @Provides
    @Reusable
    @JvmStatic
    internal fun providePagingConfig(): PagedList.Config {
        return PagedList.Config.Builder()
            .setPageSize(BuildConfig.PAGE_SIZE)
            .setEnablePlaceholders(false)
            .build()
    }

//    @Provides
//    @Reusable
//    @JvmStatic
//    internal fun providePagedDataSource(interactor: ComputerDbInteractorInterface): BasePagedKeyDataSource {
//        return ComputerListDataSource(interactor)
//    }

//    @Provides
//    @Reusable
//    @JvmStatic
//    internal fun provideDataSourceFactory(dataSource: BasePagedKeyDataSource): ComputerListDataSourceFactory {
//        return ComputerListDataSourceFactory(dataSource)
//    }

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideDataSourceFactory(interactor: ComputerDbInteractorInterface): ComputerListDataSourceFactory {
        return ComputerListDataSourceFactory(interactor)
    }

//    @Provides
//    @Reusable
//    @JvmStatic
//    internal fun provideLivePagedListBuilder(dataSourceFactory: ComputerListDataSourceFactory, config: PagedList.Config): LiveData<PagedList<Computer>> {
//        return LivePagedListBuilder<Int, Computer>(dataSourceFactory, config)
//            .setFetchExecutor(Executors.newSingleThreadExecutor())
//            .build()
//    }

}