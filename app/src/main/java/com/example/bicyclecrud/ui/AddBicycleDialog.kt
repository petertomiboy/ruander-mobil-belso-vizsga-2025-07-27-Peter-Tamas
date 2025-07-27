package com.example.bicyclecrud.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.bicyclecrud.model.Bicycle

@Composable
fun AddBicycleDialog(
    onDismiss: () -> Unit,
    onSave: (Bicycle) -> Unit,
    bicycleToEdit: Bicycle? = null
) {
    var manufacturer by remember { mutableStateOf(bicycleToEdit?.manufacturer ?: "") }
    var model by remember { mutableStateOf(bicycleToEdit?.model ?: "") }
    var priceText by remember { mutableStateOf(bicycleToEdit?.price?.toString() ?: "") }
    var isActive by remember { mutableStateOf(bicycleToEdit?.isActive ?: true) }

    var error by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                val price = priceText.toIntOrNull()
                when {
                    manufacturer.length < 2 -> error = "Gyártó legalább 2 karakter legyen"
                    model.length < 3 -> error = "Modell legalább 3 karakter legyen"
                    price == null || price < 0 || price > 10_000_000 -> error = "Érvénytelen ár"
                    else -> {
                        onSave(
                            Bicycle(
                                id = bicycleToEdit?.id ?: 0,
                                manufacturer = manufacturer,
                                model = model,
                                price = price,
                                isActive = isActive,
                                isDeleted = bicycleToEdit?.isDeleted ?: false
                            )
                        )
                        onDismiss()
                    }
                }
            }) {
                Text("Mentés")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Mégse")
            }
        },
        title = {
            Text(if (bicycleToEdit != null) "Kerékpár módosítása" else "Új kerékpár felvétele")
        },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                if (error.isNotEmpty()) {
                    Text(text = error, color = MaterialTheme.colorScheme.error)
                }
                OutlinedTextField(
                    value = manufacturer,
                    onValueChange = { manufacturer = it },
                    label = { Text("Gyártó") },
                    singleLine = true
                )
                OutlinedTextField(
                    value = model,
                    onValueChange = { model = it },
                    label = { Text("Modell") },
                    singleLine = true
                )
                OutlinedTextField(
                    value = priceText,
                    onValueChange = { priceText = it },
                    label = { Text("Nettó ár") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(checked = isActive, onCheckedChange = { isActive = it })
                    Text("Aktív")
                }
            }
        }
    )
}