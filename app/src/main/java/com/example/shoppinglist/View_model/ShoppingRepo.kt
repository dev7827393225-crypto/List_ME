package com.example.shoppinglist.View_model

import androidx.lifecycle.LiveData
import com.example.shoppinglist.database.database_Dao
import com.example.shoppinglist.database.database_model

class ShoppingRepo(private val dao: database_Dao) {

    val allItem: LiveData<List<database_model>> = dao.getAllItems()


    suspend fun insert(item: database_model) = dao.addItem(item)

    suspend fun update(item: database_model) = dao.updateItem(item)

    suspend fun delete(item: database_model) = dao.deleteItem(item)



}