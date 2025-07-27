package com.example.bicyclecrud.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bicycles")
data class Bicycle(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val manufacturer: String,
    val model: String,
    val price: Int,
    val isActive: Boolean,
    var isDeleted: Boolean = false
)
