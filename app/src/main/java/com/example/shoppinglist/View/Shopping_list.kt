package com.example.shoppinglist.View

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.shoppinglist.View_model.SL_ViewModel
import com.example.shoppinglist.database.database_model

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingList(
    viewModel: SL_ViewModel = viewModel()
) {
    val shoppingItems by viewModel.allitem.observeAsState(emptyList())

    var showDialog by remember { mutableStateOf(false) }
    var newItemName by remember { mutableStateOf("") }
    var newItemQuantity by remember { mutableStateOf("1") }

    Scaffold(
        modifier = Modifier.fillMaxSize(),

        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.ShoppingCart,
                            contentDescription = null
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = "SHOPPING LIST",
                            fontWeight = FontWeight.Normal
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.inversePrimary
                )
            )
        },

        floatingActionButton = {
            FloatingActionButton(onClick = { showDialog = true }) {
                Icon(Icons.Default.Add, contentDescription = "Add Item")
            }
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(paddingValues)
        ) {

            LazyColumn(
                modifier = Modifier.padding(top = 10.dp)
            ) {
                items(shoppingItems) { item ->
                    ShoppingListItemRow(
                        item = item,
                        onEdit = { updatedName, updatedQuantity ->
                            viewModel.update(
                                item.copy(
                                    itemName = updatedName,
                                    itemQuantity = updatedQuantity
                                )
                            )
                        },
                        onDelete = {
                            viewModel.delete(item)
                        }
                    )
                }
            }

            if (showDialog) {
                AddItemDialog(
                    itemName = newItemName,
                    itemQuality = newItemQuantity,
                    onItemNameChange = { newItemName = it },
                    onItemQualityChange = { newItemQuantity = it },
                    onDismiss = { showDialog = false },
                    onConform = {
                        val quantity = newItemQuantity.toIntOrNull() ?: 1
                        if (newItemName.isNotBlank()) {
                            viewModel.insert(
                                database_model(
                                    id = 0,
                                    itemName = newItemName,
                                    itemQuantity = quantity,
                                    isPurchased = false
                                )
                            )
                            newItemName = ""
                            newItemQuantity = "1"
                            showDialog = false
                        }
                    }
                )
            }
        }
    }
}
