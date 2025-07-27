package com.example.bicyclecrud.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.bicyclecrud.model.Bicycle

@Database(
    entities = [Bicycle::class],
    version = 1,
    exportSchema = false
)
abstract class BicycleDatabase : RoomDatabase() {
    abstract fun bicycleDao(): BicycleDao
}