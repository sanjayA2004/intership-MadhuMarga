package com.example.madhumarga.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "hive_logs",
    foreignKeys = [
        ForeignKey(
            entity = Hive::class,
            parentColumns = ["id"],
            childColumns = ["hiveId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class HiveLog(
    @PrimaryKey(autoGenerate = true) val logId: Int = 0,
    val hiveId: Int,
    val date: Long = System.currentTimeMillis(),
    val inspectionNotes: String,
    val treatmentAdministered: String = "",
    val feedingDetails: String = "",
    val healthStatus: String // e.g., Healthy, Varroa detected, Weak
)
