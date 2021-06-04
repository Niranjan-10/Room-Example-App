package com.example.roomapp.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.roomapp.repository.DatabaseHelper
import com.example.roomapp.ui.DatabaseViewModel

class ViewModelFactory(private val dbHelper: DatabaseHelper) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DatabaseViewModel::class.java)) {
            return DatabaseViewModel(dbHelper) as T
        }

        throw IllegalArgumentException("Unknown class name")
    }
}