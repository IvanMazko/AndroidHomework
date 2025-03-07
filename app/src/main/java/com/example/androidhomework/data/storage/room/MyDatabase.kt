package com.example.androidhomework.data.storage.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.androidhomework.data.model.User
import com.example.androidhomework.domain.model.Note

@Database(entities = [Note::class, User::class], version = 3)
abstract class MyDatabase : RoomDatabase() {
    abstract fun noteDao() : NoteDao
    abstract fun userDao(): UserDao
}