package com.example.androidhomework.presentation.view_model

import android.os.Bundle
import androidx.appcompat.widget.AppCompatEditText
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidhomework.presentation.actions.RegistrationFragmentAction
import com.example.androidhomework.presentation.view.fragments.MainFragment

class RegistrationFragmentViewModel: ViewModel() {

    data class CurrentState(
        val loginText: Bundle = Bundle()
    )

    private val _liveData = MutableLiveData<CurrentState>()
    val liveData : LiveData<CurrentState> get() = _liveData

    fun handleAction(action: RegistrationFragmentAction, login: AppCompatEditText){
        when(action){
            RegistrationFragmentAction.ToNextScreen ->  toNextScreen(login)
        }
    }
    private fun toNextScreen(login: AppCompatEditText) {
        val args = Bundle()
        args.putString("username", login.text.toString())
        _liveData.value = CurrentState(loginText = args)
    }
}