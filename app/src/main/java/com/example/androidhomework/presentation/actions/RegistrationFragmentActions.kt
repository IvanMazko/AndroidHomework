package com.example.androidhomework.presentation.actions

sealed class RegistrationFragmentActions {
    data object GoToSignInScreen : RegistrationFragmentActions()
    data class GoToMainScreen(val userName : String) : RegistrationFragmentActions()
}