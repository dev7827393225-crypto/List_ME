package com.example.shoppinglist.View_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.shoppinglist.database.database_model
import com.example.shoppinglist.database.shDatabase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SL_ViewModel(application: Application): AndroidViewModel(application) {
var item:database_model?=null
    var isedit= MutableStateFlow(false)
    var isEditing=isedit.asStateFlow()

    private val repository : ShoppingRepo
    var allitem: LiveData<List<database_model>>

    init{
var dao= shDatabase.getDataBase(application).databaseDao()
        repository= ShoppingRepo(dao)
       allitem= repository.allItem


    }

    fun insert(item: database_model){
        viewModelScope.launch {
            repository.insert(item)
        }
    }

    fun delete(item: database_model){
        viewModelScope.launch {
            repository.delete(item)
        }
    }

    fun update(item: database_model){
        viewModelScope.launch {
            repository.update(item)
        }
    }

}