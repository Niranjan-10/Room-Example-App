package com.example.roomapp.database

import android.content.Context
import androidx.room.*
import com.example.roomapp.data.Note
import com.example.roomapp.data.NoteDao

@Database(entities = arrayOf(Note::class), version = 1)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao

//    companion object {
//        @Volatile
//        private var noteDatabase: NoteDatabase? = null
//        private val LOCK = Any()
//
//
//        operator fun invoke(context: Context) = noteDatabase ?: synchronized(LOCK) {
//            noteDatabase ?: buildDatabase(context).also {
//                noteDatabase = it
//            }
//        }
//
//        private fun buildDatabase(context: Context) = Room.databaseBuilder(
//            context.applicationContext,
//            NoteDatabase::class.java,
//            "note_database"
//        )
//            .fallbackToDestructiveMigration()
//            .build()
//    }

}