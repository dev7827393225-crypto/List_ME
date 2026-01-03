package com.example.shoppinglist.database

import androidx.room.Entity
import androidx.room.PrimaryKey



@Entity(tableName = "Database_Table")
data class database_model(
    @PrimaryKey(autoGenerate = true)
    val id: Int
    ,val itemName: String
    ,val itemQuantity: Int=1
    ,val isPurchased:Boolean=false

)
