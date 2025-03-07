package com.example.androidhomework.presentation.actions

import com.example.androidhomework.domain.model.Note

sealed class MainFragmentAction {
    data object ReturnToRegistration: MainFragmentAction()
    data object AddNewNote : MainFragmentAction()
    data class SetUserName(val username: String) : MainFragmentAction()
    data class SetNoteList(val list: List<Note>) : MainFragmentAction()
}