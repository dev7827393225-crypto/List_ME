package com.example.shoppinglist.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update


@Dao
interface database_Dao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addItem(item: database_model)

    @Delete
    suspend fun deleteItem(item: database_model)

    @Update
    suspend fun updateItem(item: database_model)

@Query("SELECT * FROM 'Database_Table' ORDER BY id ASC")
 fun getAllItems(): LiveData<List<database_model>>



}