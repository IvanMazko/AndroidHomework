package com.example.androidhomework.presentation.actions

sealed class NewNoteFragmentActions {
    data class ChangeProgressBarStatus(val flag: Boolean) : NewNoteFragmentActions()
}