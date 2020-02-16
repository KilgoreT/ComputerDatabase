package com.example.computerdatabase.datasource.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.computerdatabase.datasource.room.dao.ComputerDetailDao
import com.example.computerdatabase.entity.ComputerDetail

@Database(entities = [ComputerDetail::class], version = AppDatabase.VERSION, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun computerDetailDao(): ComputerDetailDao

    companion object {
        const val VERSION = 1
        const val DATABASE_NAME = "ComputerDatabase"
    }
}