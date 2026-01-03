package com.example.shoppinglist.View

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.shoppinglist.View_model.SL_ViewModel
import com.example.shoppinglist.database.database_model


@Composable
fun ShoppingListItemRow(
    item: database_model,
    onEdit: (String, Int) -> Unit,
    onDelete: () -> Unit
) {
    val vm: SL_ViewModel= viewModel()
    var isEditing by remember { mutableStateOf(false) }
    var editedName by remember { mutableStateOf(item.itemName) }
    var editedQuantity by remember { mutableStateOf(item.itemQuantity.toString()) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(7.dp),
        shape = CardDefaults.elevatedShape
    ) {
        if (!isEditing) {
            Row(
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = item.itemName, style = MaterialTheme.typography.titleMedium)
                    Text(
                        text = "Quantity: ${item.itemQuantity}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                IconButton(onClick = { isEditing = true }) {
                    Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit")
                }
                IconButton(onClick = onDelete) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete")
                }
            }
        } else {
            Column(modifier = Modifier.padding(12.dp)) {
                OutlinedTextField(
                    value = editedName,
                    onValueChange = { editedName = it },
                    label = { Text("Name") },
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = editedQuantity,
                    onValueChange = { editedQuantity = it.filter { ch -> ch.isDigit() } },
                    label = { Text("Quantity") },
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row {
                    Button(onClick = {
                        val q = editedQuantity.toIntOrNull() ?: 1
                        onEdit(editedName, q)
                        isEditing = false
                    }) {
                        Text("Save")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    TextButton(onClick = {
                        editedName = item.itemName
                        editedQuantity = item.itemQuantity.toString()
                        isEditing = false
                    }) {
                        Text("Cancel")
                    }
                }
            }
        }
    }
}