package com.example.androidhomework.presentation.actions

sealed class MainFragmentAction {
//    data object ReturnToRegistration: MainFragmentAction()
//    data object ToNewNoteScreen: MainFragmentAction()
    data object SetUserName: MainFragmentAction()
    data object SetListOfNotes: MainFragmentAction()
}