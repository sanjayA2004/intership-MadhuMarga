package com.example.madhumarga.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.madhumarga.data.Hive
import com.example.madhumarga.data.HiveLog
import com.example.madhumarga.data.HiveDatabase
import com.example.madhumarga.data.HiveRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HiveViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: HiveRepository
    val hives: StateFlow<List<Hive>>
    val allLogs: StateFlow<List<HiveLog>>

    init {
        val hiveDao = HiveDatabase.getDatabase(application).hiveDao()
        repository = HiveRepository(hiveDao)
        hives = repository.allHives.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
        allLogs = repository.allLogs.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
    }

    fun addHive(hive: Hive) {
        viewModelScope.launch {
            repository.insert(hive)
        }
    }

    fun addLog(log: HiveLog) {
        viewModelScope.launch {
            repository.insertLog(log)
        }
    }

    fun getLogsForHive(hiveId: Int) = repository.getLogsForHive(hiveId)

    fun deleteHive(hive: Hive) {
        viewModelScope.launch {
            repository.delete(hive)
        }
    }

    fun getSuggestion(hive: Hive): String {
        return when {
            !hive.queenPresent -> "Add Queen Bee"
            hive.activityLevel == "Low" -> "Intervention Needed"
            hive.honeyCollected > 50 -> "Ready for Harvest"
            else -> "Healthy"
        }
    }
}
