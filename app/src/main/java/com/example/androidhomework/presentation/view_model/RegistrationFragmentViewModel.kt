package com.example.androidhomework.presentation.view_model

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RegistrationFragmentViewModel: ViewModel() {

    private val liveData: MutableLiveData<Bundle?> = MutableLiveData(null)
    val publicLiveData: LiveData<Bundle?> = liveData


    fun checkData(login: String, password:String) {
        if (login.isEmpty() || password.isEmpty()) {
            liveData.value = null
        } else {
            val args = Bundle()
            args.putString("username", login)
            liveData.value = args
        }

    }


}