package com.example.androidhomework.presentation.view_model

import android.os.Bundle
import androidx.appcompat.widget.AppCompatEditText
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidhomework.presentation.view.fragments.MainFragment

class RegistrationFragmentViewModel: ViewModel() {

    fun toNextScreen(login: String): Bundle {
        val args = Bundle()
        args.putString("username", login)
        return args
    }
}