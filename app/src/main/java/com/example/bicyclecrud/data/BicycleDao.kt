package com.example.bicyclecrud.data

import androidx.room.*
import com.example.bicyclecrud.model.Bicycle
import kotlinx.coroutines.flow.Flow

@Dao
interface BicycleDao {

    @Query("SELECT * FROM bicycles WHERE isDeleted = 0 ORDER BY id DESC")
    fun getAll(): Flow<List<Bicycle>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(bicycle: Bicycle)

    @Update
    suspend fun update(bicycle: Bicycle)

    @Delete
    suspend fun delete(bicycle: Bicycle)


    @Query("UPDATE bicycles SET isDeleted = 1 WHERE id = :id")
    suspend fun softDelete(id: Int)


    @Query("SELECT COUNT(*) FROM bicycles WHERE isDeleted = 0")
    suspend fun countAll(): Int


    @Query("SELECT * FROM bicycles WHERE id = :id LIMIT 1")
    suspend fun getBicycleById(id: Int): Bicycle?


    @Query("SELECT * FROM bicycles ORDER BY id DESC")
    fun getAllIncludingDeleted(): Flow<List<Bicycle>>


    @Query("SELECT * FROM bicycles WHERE isDeleted = 1 ORDER BY id DESC")
    fun getDeleted(): Flow<List<Bicycle>>


    @Query("UPDATE bicycles SET isDeleted = 0 WHERE id = :id")
    suspend fun restoreBicycle(id: Int)


    @Query("SELECT * FROM bicycles WHERE isDeleted = 0 AND (manufacturer LIKE '%' || :searchQuery || '%' OR model LIKE '%' || :searchQuery || '%') ORDER BY id DESC")
    fun searchBicycles(searchQuery: String): Flow<List<Bicycle>>
}