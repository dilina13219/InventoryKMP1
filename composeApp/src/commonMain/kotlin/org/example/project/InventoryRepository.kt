package org.example.project

import app.cash.sqldelight.db.SqlDriver
import com.inventory.db.InventoryDatabase
import com.inventory.db.InventoryItem

class InventoryRepository(driver: SqlDriver) {
    private val database = InventoryDatabase(driver)
    private val dbQueries = database.inventoryQueries

    fun addItem(name: String, quantity: Long, category: String, price: Double) {
        dbQueries.insertItem(name, quantity, category, price)
    }

    fun getAllItems(): List<InventoryItem> {
        return dbQueries.selectAllItems().executeAsList()
    }

    fun deleteItem(id: Long) {
        dbQueries.deleteItem(id)
    }

    fun updateItem(id: Long, name: String, quantity: Long, category: String, price: Double) {
        dbQueries.updateItem(name, quantity, category, price, id)
    }
}