package com.example.bicyclecrud.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bicyclecrud.model.Bicycle

@Composable
fun MainScreen(viewModel: MainViewModel = hiltViewModel()) {
    val bicycles by viewModel.bicycles.collectAsState()
    var showDialog by remember { mutableStateOf(false) }
    var bicycleToEdit by remember { mutableStateOf<Bicycle?>(null) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                bicycleToEdit = null
                showDialog = true
            }) {
                Text("+")
            }
        }
    ) { paddingValues ->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(bicycles.filter { !it.isDeleted }) { bicycle ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                bicycleToEdit = bicycle
                                showDialog = true
                            }
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(text = "${bicycle.manufacturer} ${bicycle.model}")
                            Text(text = "Ár: ${bicycle.price} Ft")
                            Text(text = if (bicycle.isActive) "Aktív" else "Inaktív")
                        }
                    }
                }
            }

            if (showDialog) {
                AddBicycleDialog(
                    onDismiss = { showDialog = false },
                    onSave = {
                        if (bicycleToEdit == null) {
                            viewModel.insert(it)
                        } else {
                            viewModel.update(it)
                        }
                        showDialog = false
                    },
                    bicycleToEdit = bicycleToEdit
                )
            }
        }
    }
}