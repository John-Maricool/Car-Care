package com.maricool.carcare.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CarModel::class], version = 1, exportSchema = false)
abstract class MyDatabase: RoomDatabase() {
    abstract fun carDao(): CarDao
}