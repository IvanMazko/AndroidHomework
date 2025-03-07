package com.example.androidhomework.presentation.actions

import com.example.androidhomework.data.storage.room.UserDao

sealed class RegistrationFragmentActions {
    data object GoToSignInScreen : RegistrationFragmentActions()
    data class GoToMainScreen(val userName : String) : RegistrationFragmentActions()
    data class RegisterUser(val userName: String, val password: String, val dao: UserDao) : RegistrationFragmentActions()
}