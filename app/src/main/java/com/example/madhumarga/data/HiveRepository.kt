package com.example.madhumarga.data

import kotlinx.coroutines.flow.Flow

class HiveRepository(private val hiveDao: HiveDao) {
    val allHives: Flow<List<Hive>> = hiveDao.getAllHives()

    suspend fun insert(hive: Hive) {
        hiveDao.insertHive(hive)
    }

    suspend fun delete(hive: Hive) {
        hiveDao.deleteHive(hive)
    }

    suspend fun insertLog(log: HiveLog) {
        hiveDao.insertLog(log)
    }

    fun getLogsForHive(hiveId: Int): Flow<List<HiveLog>> = hiveDao.getLogsForHive(hiveId)

    val allLogs: Flow<List<HiveLog>> = hiveDao.getAllLogs()
}
