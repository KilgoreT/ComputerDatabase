package com.example.computerdatabase.di

import com.example.computerdatabase.ui.computerList.ComputerListDataSource
import com.example.computerdatabase.ui.computerList.ComputerListViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, RepositoryModule::class, InteractorModule::class, PagingModule::class])
interface AppComponent {

    fun inject(target: ComputerListViewModel)
    fun inject(target: ComputerListDataSource)

    @Component.Builder
    interface Builder {
        fun build(): AppComponent
//        fun appContextModule(contextModule: AppContextModule): Builder
    }

}