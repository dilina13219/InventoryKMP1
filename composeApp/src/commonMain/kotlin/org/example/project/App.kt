package org.example.project

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// --- Assignment Requirement: Centralized Theming ---
object AppTheme {
    val PrimaryColor = Color(0xFF6200EE)
    val SecondaryColor = Color(0xFF03DAC6)
    val TitleStyle = TextStyle(
        fontSize = 22.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Black
    )
}

@Composable
fun App(driverFactory: DatabaseDriverFactory) {
    // Custom Theme එක පාවිච්චි කිරීම
    MaterialTheme(
        colorScheme = lightColorScheme(primary = AppTheme.PrimaryColor, secondary = AppTheme.SecondaryColor)
    ) {
        val repository = remember { InventoryRepository(driverFactory.createDriver()) }

        var name by remember { mutableStateOf("") }
        var quantity by remember { mutableStateOf("") }
        var category by remember { mutableStateOf("") }
        var price by remember { mutableStateOf("") }
        var searchQuery by remember { mutableStateOf("") }
        var editingItemId by remember { mutableStateOf<Long?>(null) }
        var itemList by remember { mutableStateOf(repository.getAllItems()) }

        val filteredList = itemList.filter { it.name.contains(searchQuery, ignoreCase = true) }

        Column(modifier = Modifier.fillMaxSize().padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Inventory Management", style = AppTheme.TitleStyle)
            Spacer(modifier = Modifier.height(16.dp))

            TextField(value = name, onValueChange = { name = it }, label = { Text("Item Name") }, modifier = Modifier.fillMaxWidth())
            TextField(value = category, onValueChange = { category = it }, label = { Text("Category") }, modifier = Modifier.fillMaxWidth())

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                TextField(value = quantity, onValueChange = { quantity = it }, label = { Text("Qty") }, modifier = Modifier.weight(1f))
                TextField(value = price, onValueChange = { price = it }, label = { Text("Price") }, modifier = Modifier.weight(1f))
            }

            Button(
                onClick = {
                    if (name.isNotEmpty() && quantity.isNotEmpty() && price.isNotEmpty() && category.isNotEmpty()) {
                        val id = editingItemId
                        if (id != null) {
                            repository.updateItem(id, name, quantity.toLong(), category, price.toDouble())
                            editingItemId = null
                        } else {
                            repository.addItem(name, quantity.toLong(), category, price.toDouble())
                        }
                        itemList = repository.getAllItems()
                        name = ""; quantity = ""; price = ""; category = ""
                    }
                },
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
            ) {
                Text(if (editingItemId != null) "Update Item" else "Add Item")
            }

            OutlinedTextField(
                value = searchQuery, onValueChange = { searchQuery = it },
                label = { Text("Search by Name...") }, modifier = Modifier.fillMaxWidth(),
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) }
            )

            LazyColumn(modifier = Modifier.weight(1f).fillMaxWidth()) {
                items(filteredList) { item ->
                    Card(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
                        Row(modifier = Modifier.padding(16.dp).fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                            Column(modifier = Modifier.weight(1f).clickable {
                                name = item.name
                                quantity = item.quantity.toString()
                                price = item.price.toString()
                                category = item.category
                                editingItemId = item.id
                            }) {
                                Text(text = item.name, style = MaterialTheme.typography.titleMedium)
                                Text(text = "Category: ${item.category} | Qty: ${item.quantity} | Rs. ${item.price}", style = MaterialTheme.typography.bodySmall)
                            }
                            IconButton(onClick = { repository.deleteItem(item.id); itemList = repository.getAllItems() }) {
                                Icon(Icons.Default.Delete, contentDescription = "Delete", tint = Color.Red)
                            }
                        }
                    }
                }
            }
        }
    }
}