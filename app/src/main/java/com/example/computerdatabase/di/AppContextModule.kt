package com.example.computerdatabase.di

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppContextModule(val context: Context) {

    @Provides
    @Singleton
    fun providesAppContext(): Context {
        return context
    }

}