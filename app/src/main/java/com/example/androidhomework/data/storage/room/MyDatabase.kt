package com.example.androidhomework.data.storage.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.androidhomework.domain.model.Note

@Database(entities = [Note::class], version = 1)
abstract class MyDatabase : RoomDatabase() {
    abstract fun noteDao() : NoteDao
}