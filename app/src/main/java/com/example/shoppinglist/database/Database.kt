package com.example.shoppinglist.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase



@Database(entities = [database_model::class], version = 1, exportSchema = false)
abstract class shDatabase: RoomDatabase() {

    abstract fun databaseDao(): database_Dao

    companion object{

        @Volatile
        private var INSTANCE: shDatabase? = null

        fun getDataBase(context: Context):shDatabase{
            return INSTANCE ?: synchronized(this){
                val instance= Room.databaseBuilder(
                    context.applicationContext
                    , shDatabase::class.java
                    ,"Database"
                ).build()
                INSTANCE=instance
                instance
            }

        }


    }

}