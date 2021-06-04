package com.example.roomapp.repository

import com.example.roomapp.data.Note
import com.example.roomapp.database.NoteDatabase

class DatabaseHelperImpl(private val noteDatabase: NoteDatabase):DatabaseHelper {
    override suspend fun getNotes(): List<Note> = noteDatabase.noteDao().getAllNotes()

    override suspend fun insertNote(note: Note) = noteDatabase.noteDao().insert(note)
    override suspend fun updateNote(note: Note) = noteDatabase.noteDao().update(note)
}