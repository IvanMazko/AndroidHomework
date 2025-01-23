package com.example.androidhomework.presentation.actions

sealed class MainFragmentAction {
    data object ReturnToRegistration: MainFragmentAction()
    data object AddNewNote: MainFragmentAction()
    data object SetUserName: MainFragmentAction()
    data object SetNoteList: MainFragmentAction()
}