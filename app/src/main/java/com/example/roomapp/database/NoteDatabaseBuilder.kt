package com.example.roomapp.database

import android.content.Context
import androidx.room.Room

object NoteDatabaseBuilder {

    private var INSTANCE: NoteDatabase? = null

    fun getInstance(context: Context): NoteDatabase {
        if (INSTANCE == null) {
            synchronized(NoteDatabase::class) {
                INSTANCE = buildRoomDB(context)
            }
        }

        return INSTANCE!!
    }

    private fun buildRoomDB(context: Context) =
        Room.databaseBuilder(context.applicationContext, NoteDatabase::class.java, "note_database")
            .build()
}