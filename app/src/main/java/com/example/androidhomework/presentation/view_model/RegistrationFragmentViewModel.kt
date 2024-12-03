package com.example.androidhomework.presentation.view_model

import android.os.Bundle
import androidx.appcompat.widget.AppCompatEditText
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidhomework.presentation.view.fragments.MainFragment

class RegistrationFragmentViewModel: ViewModel() {

//    private val liveData: MutableLiveData<String> = MutableLiveData(null)
//    val publicLiveData: LiveData<String> = liveData

    fun toNextScreen(login: AppCompatEditText): MainFragment {
        val mainFragment = MainFragment()
        val args = Bundle()
        args.putString("username", login.text.toString())
        mainFragment.arguments = args
        return mainFragment
    }
}