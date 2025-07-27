package com.example.bicyclecrud

import android.content.Context
import androidx.room.Room
import com.example.bicyclecrud.data.BicycleDao
import com.example.bicyclecrud.data.BicycleDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): BicycleDatabase {
        return Room.databaseBuilder(
            context,
            BicycleDatabase::class.java,
            "bicycle_database"
        ).build()
    }

    @Provides
    fun provideBicycleDao(database: BicycleDatabase): BicycleDao {
        return database.bicycleDao()
    }
}