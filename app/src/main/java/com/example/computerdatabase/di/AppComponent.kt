package com.example.computerdatabase.di

import com.example.computerdatabase.ui.computerDetail.ComputerDetailViewModel
import com.example.computerdatabase.ui.computerList.ComputerListViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppContextModule::class, NetworkModule::class, DatabaseModule::class, RepositoryModule::class, InteractorModule::class, PagingModule::class])
interface AppComponent {

    fun inject(target: ComputerListViewModel)
    fun inject(target: ComputerDetailViewModel)

    @Component.Builder
    interface Builder {
        fun appContextModule(contextModule: AppContextModule): Builder
        fun build(): AppComponent
    }

}