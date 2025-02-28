package com.example.androidhomework.presentation.actions

sealed class SignInFragmentActions {
    data object GoToRegistrationScreen : SignInFragmentActions()
    data class GoToMainScreen(val userName : String) : SignInFragmentActions()
}