package com.example.shoppinglist.View

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AddItemDialog(
    itemName: String,
    itemQuality: String,
    onItemNameChange: (String) -> Unit,
    onItemQualityChange: (String) -> Unit,
    onDismiss: () -> Unit,
    onConform: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Add New Item") },
        text = {
            Column {
                OutlinedTextField(
                    value = itemName,
                    onValueChange = onItemNameChange,
                    label = { Text("Name") },
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = itemQuality,
                    onValueChange = { newVal -> onItemQualityChange(newVal.filter { it.isDigit() }) },
                    label = { Text("Quantity") },
                    singleLine = true
                )
            }
        },
        confirmButton = {
            TextButton(onClick = onConform) {
                Text("Add")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}
