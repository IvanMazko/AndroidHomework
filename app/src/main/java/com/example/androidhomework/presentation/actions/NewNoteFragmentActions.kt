package com.example.androidhomework.presentation.actions

import com.example.androidhomework.data.model.User
import com.example.androidhomework.data.storage.room.NoteDao
import com.example.androidhomework.data.storage.room.UserDao
import com.example.androidhomework.domain.model.Note

sealed class NewNoteFragmentActions {
    data class SaveNote(val header: String, val message : String, val noteDao: NoteDao, val dateText : String, val currentUser : User) : NewNoteFragmentActions()
}