package com.example.androidhomework.presentation.actions

import com.example.androidhomework.domain.model.Note

sealed class MainFragmentActions {
    data object ReturnToRegistration: MainFragmentActions()
    data object AddNewNote : MainFragmentActions()
    data object ShowAllCharactersInLogs : MainFragmentActions()
    data object ShowCurrentCharacterByIdInLogs : MainFragmentActions()
    data object ShowCurrentCharacterByNameInLogs : MainFragmentActions()
    data class SetUserName(val username: String) : MainFragmentActions()
    data class SetNoteList(val list: ArrayList<Note>?) : MainFragmentActions()
}