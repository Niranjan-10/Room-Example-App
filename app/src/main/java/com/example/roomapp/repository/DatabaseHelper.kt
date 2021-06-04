package com.example.roomapp.repository

import android.app.Application
import com.example.roomapp.data.Note


interface DatabaseHelper{
    suspend fun getNotes(): List<Note>
    suspend fun insertNote(note:Note)
    suspend fun updateNote(note: Note)
}