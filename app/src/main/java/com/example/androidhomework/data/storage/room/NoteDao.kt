package com.example.androidhomework.data.storage.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.androidhomework.domain.model.Note

@Dao
interface NoteDao {
    @Insert(entity = Note::class, onConflict = OnConflictStrategy.REPLACE)
    fun putNote(note: Note)

    @Query("SELECT * FROM Note WHERE userId = :userId")
    fun getNotesByUserId(userId: Int): List<Note>

    @Query("DELETE FROM Note WHERE id = :noteId")
    suspend fun deleteNoteById(noteId: Int)
}