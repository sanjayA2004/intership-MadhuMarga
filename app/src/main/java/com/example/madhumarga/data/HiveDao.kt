package com.example.madhumarga.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface HiveDao {
    @Insert
    suspend fun insertHive(hive: Hive)

    @Query("SELECT * FROM hives")
    fun getAllHives(): Flow<List<Hive>>
    
    @Delete
    suspend fun deleteHive(hive: Hive)

    @Insert
    suspend fun insertLog(log: HiveLog)

    @Query("SELECT * FROM hive_logs WHERE hiveId = :hiveId ORDER BY date DESC")
    fun getLogsForHive(hiveId: Int): Flow<List<HiveLog>>

    @Query("SELECT * FROM hive_logs ORDER BY date DESC")
    fun getAllLogs(): Flow<List<HiveLog>>
}
