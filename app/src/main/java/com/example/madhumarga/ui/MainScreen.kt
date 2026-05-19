package com.example.madhumarga.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.madhumarga.data.Hive
import com.example.madhumarga.viewmodel.HiveViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(viewModel: HiveViewModel, onAddHiveClick: () -> Unit) {
    val hiveList by viewModel.hives.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Madhu Marga - Hive Manager") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddHiveClick) {
                Icon(Icons.Default.Add, contentDescription = "Add Hive")
            }
        }
    ) { padding ->
        if (hiveList.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Text("No hives added yet. Tap + to start!", style = MaterialTheme.typography.bodyLarge)
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(hiveList) { hive ->
                    HiveCard(
                        hive = hive,
                        suggestion = viewModel.getSuggestion(hive),
                        onDelete = { viewModel.deleteHive(hive) }
                    )
                }
            }
        }
    }
}

@Composable
fun HiveCard(hive: Hive, suggestion: String, onDelete: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Location: ${hive.location}",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                IconButton(onClick = onDelete) {
                    Icon(Icons.Default.Delete, contentDescription = "Delete", tint = Color.Red)
                }
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("Honey Collected: ${hive.honeyCollected} kg")
                Text("Activity: ${hive.activityLevel}")
            }
            
            Text("Queen Present: ${if (hive.queenPresent) "Yes" else "No"}")
            
            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
            
            Text(
                text = "Suggestion: $suggestion",
                style = MaterialTheme.typography.bodyMedium,
                color = if (suggestion == "Healthy") Color(0xFF2E7D32) else Color.Red,
                fontWeight = FontWeight.Medium
            )
        }
    }
}
