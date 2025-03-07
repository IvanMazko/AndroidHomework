package com.example.androidhomework.presentation.view_model

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidhomework.data.model.User
import com.example.androidhomework.data.storage.room.UserDao
import com.example.androidhomework.presentation.actions.RegistrationFragmentActions
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class RegistrationFragmentViewModel : ViewModel() {

    data class CurrentState(
        val toSignInScreenBtn : Boolean = false,
        val toMainScreenBtn : Boolean = false,
        val userName : Bundle = Bundle()
    )

    private val _liveData = MutableLiveData<CurrentState>()
    val liveData: LiveData<CurrentState> get() = _liveData

    init {
        _liveData.value = CurrentState()
    }

    fun handleAction(actions: RegistrationFragmentActions){
        when(actions){
            is RegistrationFragmentActions.GoToSignInScreen -> _liveData.value = _liveData.value?.copy(toSignInScreenBtn = true)
            is RegistrationFragmentActions.GoToMainScreen -> toNextScreen(actions.userName)
            is RegistrationFragmentActions.RegisterUser -> registerUser(actions.userName, actions.password, actions.dao)
        }
    }

    private fun toNextScreen(login: String){
        val args = Bundle()
        args.putString("username", login)
        _liveData.value = _liveData.value?.copy(toMainScreenBtn = true, userName = args)
    }

    private fun registerUser(userName : String, userPassword : String, dao: UserDao){
        viewModelScope.launch(Dispatchers.IO) {
            dao.insertUser(User(0, username = userName, password = userPassword))
        }
    }
}