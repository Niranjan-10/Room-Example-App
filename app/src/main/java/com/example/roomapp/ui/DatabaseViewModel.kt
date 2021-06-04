package com.example.roomapp.ui

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomapp.data.Note
import com.example.roomapp.repository.DatabaseHelper
import kotlinx.coroutines.launch

class DatabaseViewModel(private val databaseHelper: DatabaseHelper) : ViewModel() {

    private val notes = MutableLiveData<List<Note>>()


    fun getNotes() = this.notes


    private fun fetchNotes() {
        viewModelScope.launch {
            try {
                val data = databaseHelper.getNotes()

                if (data.isEmpty()) {
                    Log.d("Error", "List is empty")
                } else {
                    notes.value = data
                }


            } catch (e: Exception) {
                Log.d("Error", "There is an error")
            }
        }
    }

    fun fetch() {
        fetchNotes()
    }


    private fun addNote(note: Note) {
        viewModelScope.launch {
            try {
                databaseHelper.insertNote(note)
            } catch (e: Exception) {
                Log.d("Error", "There is an error")
            }
        }
    }

    fun add(note: Note) {
        addNote(note)
    }

    private fun updateNote(note: Note) {
        viewModelScope.launch {
            try {
                databaseHelper.updateNote(note)
            } catch (e: Exception) {
                Log.d("Error", "There is an error")
            }
        }
    }

    fun update(note: Note) {
        updateNote(note)
    }
}