package com.example.computerdatabase.di

import com.example.computerdatabase.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, RepositoryModule::class, InteractorModule::class])
interface AppComponent {

    fun inject(mainActivity: MainActivity)

//    fun inject(target: MainViewModel)

    @Component.Builder
    interface Builder {
        fun build(): AppComponent
//        fun appContextModule(contextModule: AppContextModule): Builder
    }

}