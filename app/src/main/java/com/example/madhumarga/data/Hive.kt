package com.example.madhumarga.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hives")
data class Hive(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val location: String,
    val queenPresent: Boolean,
    val activityLevel: String,
    val honeyCollected: Int
)
