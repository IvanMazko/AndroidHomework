package com.example.androidhomework.presentation.actions

sealed class SignInFragmentActions {
    data object GoToRegistrationScreen : SignInFragmentActions()
    data object GoToMainScreen : SignInFragmentActions()
}