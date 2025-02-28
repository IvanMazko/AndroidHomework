package com.example.androidhomework.data.storage.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.androidhomework.domain.model.Note

@Dao
interface NoteDao {
    @Insert(entity = Note::class, onConflict = OnConflictStrategy.REPLACE)
    fun putNote(note: Note)
    @Query("SELECT * FROM note")
    fun getNote(): List<Note>
}