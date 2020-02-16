package com.example.computerdatabase.di

import android.content.Context
import androidx.room.Room
import com.example.computerdatabase.datasource.room.AppDatabase
import com.example.computerdatabase.datasource.room.dao.ComputerDetailDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(context: Context): AppDatabase {
        return Room
            .databaseBuilder(context, AppDatabase::class.java, AppDatabase.DATABASE_NAME)
            .build()
    }

    @Provides
    @Singleton
    fun provideComputerDetailDao(database: AppDatabase): ComputerDetailDao {
        return database.computerDetailDao()
    }
}