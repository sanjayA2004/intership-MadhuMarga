package com.example.madhumarga.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen() {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Settings") }) }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text("App Preferences", style = MaterialTheme.typography.titleMedium)
            
            var notificationsEnabled by remember { mutableStateOf(true) }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Enable Notifications")
                Switch(checked = notificationsEnabled, onCheckedChange = { notificationsEnabled = it })
            }

            var darkMode by remember { mutableStateOf(false) }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Dark Mode")
                Switch(checked = darkMode, onCheckedChange = { darkMode = it })
            }

            Divider()

            Text("About", style = MaterialTheme.typography.titleMedium)
            Text("Version 1.1.0")
            Text("MadhuMarga - Your Professional Beekeeping Assistant")
        }
    }
}
